package Article

import java.text.SimpleDateFormat

fun main() {
    println("== SIMPLE SSG 시작 ==")

//    articleRepository.makeTestArticles()

    memberRepository

    var login = false

    var loginid = ""

    while (true) {
        print("명령어) ")
        val command = readLineTrim()

        val rq = Rq(command)


        when (rq.actionPath) {

            "/system/exit" -> {
                println("프로그램을 종료합니다.")
                break
            }
            "/article/list" -> {

                if (login == false) {
                    println("로그인 후 이용해 주세요.")
                    continue
                }

                val page = rq.getIntParam("page", 1)
                val searchKeyword = rq.getStringParam("searchKeyword", "")

                val filteredArticles = articleRepository.getFilteredArticles(searchKeyword, page, 10)

                println("번호 / 작성자 / 제목 / 내용 / 작성날짜 / 갱신날짜")

                for (article in filteredArticles) {
                    println("${article.id} / ${article.userid} / ${article.title} / ${article.body} / ${article.regDate} / ${article.updateDate}")
                }
            }
            "/article/detail" -> {

                if (login == false) {
                    println("로그인 후 이용해 주세요.")
                    continue
                }

                val id = rq.getIntParam("id", 0)

                if (id == 0) {
                    println("id를 입력해주세요.")
                    continue
                }

                val article = articleRepository.getArticleById(id)

                if (article == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                println("번호 : ${article.id}")
                println("작성날짜 : ${article.regDate}")
                println("갱신날짜 : ${article.updateDate}")
                println("제목 : ${article.title}")
                println("내용 : ${article.body}")
            }
            "/article/modify" -> {
                val id = rq.getIntParam("id", 0)

                if (id == 0) {
                    println("id를 입력해주세요.")
                    continue
                }

                val article = articleRepository.getArticleById(id)

                if (article == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                print("${id}번 게시물 새 제목 : ")
                val title = readLineTrim()
                print("${id}번 게시물 새 내용 : ")
                val body = readLineTrim()

                articleRepository.modifyArticle(id, title, body)

                println("${id}번 게시물이 수정되었습니다.")
            }
            "/article/delete" -> {
                val id = rq.getIntParam("id", 0)

                if (id == 0) {
                    println("id를 입력해주세요.")
                    continue
                }

                val article = articleRepository.getArticleById(id)

                if (article == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                articleRepository.deleteArticle(article)
            }

            "/member/join" -> {
                print("회원 아이디 : ")
                val userid = readLine()!!.trim()

                print("회원 비밀번호 : ")
                val passwd = readLine()!!.trim()

                print("회원 이름 : ")
                val name = readLine()!!.trim()

                print("회원 닉네임 : ")
                val nick = readLine()!!.trim()

                print("회원 전화번호 : ")
                val tel = readLine()!!.trim()

                print("회원 이메일 : ")
                val email = readLine()!!.trim()

                memberRepository.addMember(userid, passwd, name, nick, tel, email)

                println("${name}님! 회원가입이 완료되었습니다.")
            }

            "/member/login" -> {

                print("아이디 입력 : ")
                val userid = readLine()!!.trim()

                print("비밀번호 입력 : ")
                val passwd = readLine()!!.trim()

                var test = memberRepository.getMemberById(userid)


                if (memberRepository.getMemberById(userid) == null) {
                    println("존재하지 않는 아이디입니다.")
                    continue

                }
                else if (passwd != test?.passwd) {
                    println("비밀번호가 일치하지 않습니다.")
                }else {
                    login = true
                    loginid = test.userid
                    println("${test.name}님 계정으로 로그인하였습니다.")
                }


            }

            "/article/write" -> {
                if (login == false) {
                    println("로그인 후 이용해 주세요.")
                    continue
                }

                print("제목 : ")
                val title = readLineTrim()

                print("내용 : ")
                val contents = readLineTrim()

                articleRepository.addArticle(title,contents, loginid)

            }
        }
    }

    println("== SIMPLE SSG 끝 ==")
}

// Rq는 UserRequest의 줄임말이다.
// Request 라고 하지 않은 이유는, 이미 선점되어 있는 클래스명 이기 때문이다.
class Rq(command: String) {
    // 데이터 예시
    // 전체 URL : /artile/detail?id=1
    // actionPath : /artile/detail
    val actionPath: String

    // 데이터 예시
    // 전체 URL : /artile/detail?id=1&title=안녕
    // paramMap : {id:"1", title:"안녕"}
    private val paramMap: Map<String, String>

    // 객체 생성시 들어온 command 를 ?를 기준으로 나눈 후 추가 연산을 통해 actionPath와 paramMap의 초기화한다.
    // init은 객체 생성시 자동으로 딱 1번 실행된다.
    init {
        // ?를 기준으로 둘로 나눈다.
        val commandBits = command.split("?", limit = 2)

        // 앞부분은 actionPath
        actionPath = commandBits[0].trim()

        // 뒷부분이 있다면
        val queryStr = if (commandBits.lastIndex == 1 && commandBits[1].isNotEmpty()) {
            commandBits[1].trim()
        } else {
            ""
        }

        paramMap = if (queryStr.isEmpty()) {
            mapOf()
        } else {
            val paramMapTemp = mutableMapOf<String, String>()

            val queryStrBits = queryStr.split("&")

            for (queryStrBit in queryStrBits) {
                val queryStrBitBits = queryStrBit.split("=", limit = 2)
                val paramName = queryStrBitBits[0]
                val paramValue = if (queryStrBitBits.lastIndex == 1 && queryStrBitBits[1].isNotEmpty()) {
                    queryStrBitBits[1].trim()
                } else {
                    ""
                }

                if (paramValue.isNotEmpty()) {
                    paramMapTemp[paramName] = paramValue
                }
            }

            paramMapTemp.toMap()
        }
    }

    fun getStringParam(name: String, default: String): String {
        return paramMap[name] ?: default
    }

    fun getIntParam(name: String, default: Int): Int {
        return if (paramMap[name] != null) {
            try {
                paramMap[name]!!.toInt()
            } catch (e: NumberFormatException) {
                default
            }
        } else {
            default
        }
    }
}

// 게시물 관련 시작
data class Article(
    val id: Int,
    val regDate: String,
    var updateDate: String,
    var title: String,
    var body: String,
    var userid : String
)

object articleRepository {
    private val articles = mutableListOf<Article>()
    private var lastId = 0

    fun deleteArticle(article: Article) {
        articles.remove(article)
    }

    fun getArticleById(id: Int): Article? {
        for (article in articles) {
            if (article.id == id) {
                return article
            }
        }

        return null
    }

    fun addArticle(title: String, body: String, userid : String) {
        val id = ++lastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()
        articles.add(Article(id, regDate, updateDate, title, body, userid))
    }

//    fun makeTestArticles() {
//        for (id in 1..100) {
//            addArticle("제목_$id", "내용_$id")
//        }
//    }

    fun modifyArticle(id: Int, title: String, body: String) {
        val article = getArticleById(id)!!

        article.title = title
        article.body = body
        article.updateDate = Util.getNowDateStr()
    }

    fun getFilteredArticles(searchKeyword: String, page: Int, itemsCountInAPage: Int): List<Article> {
        val filtered1Articles = getSearchKeywordFilteredArticles(articles, searchKeyword)
        val filtered2Articles = getPageFilteredArticles(filtered1Articles, page, itemsCountInAPage)

        return filtered2Articles
    }

    private fun getSearchKeywordFilteredArticles(articles: List<Article>, searchKeyword: String): List<Article> {
        val filteredArticles = mutableListOf<Article>()

        for (article in articles) {
            if (article.title.contains(searchKeyword)) {
                filteredArticles.add(article)
            }
        }

        return filteredArticles
    }

    private fun getPageFilteredArticles(articles: List<Article>, page: Int, itemsCountInAPage: Int): List<Article> {
        val filteredArticles = mutableListOf<Article>()

        val offsetCount = (page - 1) * itemsCountInAPage

        val startIndex = articles.lastIndex - offsetCount
        var endIndex = startIndex - (itemsCountInAPage - 1)

        if (endIndex < 0) {
            endIndex = 0
        }

        for (i in startIndex downTo endIndex) {
            filteredArticles.add(articles[i])
        }

        return filteredArticles
    }
}
// 게시물 관련 끝

// 회원 정보 관련 시작

data class Member (var userid : String, var passwd : String, var name : String, var nick : String, var tel : String, var email : String)

object memberRepository {
    private val members = mutableListOf<Member>()
    private var lastId = 0

    fun deleteMember(member : Member) {
        members.remove(member)
    }

    fun getMemberById(userid: String): Member? {
        for (member in members) {
            if (member.userid == userid) {
                return member
            }
        }

        return null
    }

    fun addMember(userid : String, passwd : String, name : String, nick : String, tel : String, email : String) {
//        val id = ++lastId
        members.add(Member(userid, passwd, name, nick, tel, email))
    }

//    fun makeTestArticles() {
//        for (id in 1..100) {
//            addMember("제목_$id", "내용_$id")
//        }
//    }

    fun modifyMember(userid : String, nick : String, passwd : String) {
        val member = getMemberById(userid)!!

        member.nick = nick
        member.passwd = passwd
    }
}


// 유틸 관련 시작
fun readLineTrim() = readLine()!!.trim()

object Util {
    fun getNowDateStr(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return format.format(System.currentTimeMillis())
    }
}
// 유틸 관련 끝