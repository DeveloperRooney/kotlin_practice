package program

import java.text.SimpleDateFormat

fun main() {
    println("== 게시판 관리 프로그램 시작 ==")

    makeTestArticles();

    loop@ while (true) {
        print("명령어) ")
        val command = readLineTrim()

        when {
            command == "system exit" -> {
                println("프로그램을 종료합니다.")
                break@loop
            }
            command.startsWith("article delete ") -> {
                val id = command.trim().split(" ")[2].toInt()

                var articleToDelete = getArticleById(id)

                if (articleToDelete == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                articles.remove(articleToDelete)

                println("${id}번 게시물을 삭제하였습니다.")
            }
            command.startsWith("article modify ") -> {
                val id = command.trim().split(" ")[2].toInt()

                var articleToModify = getArticleById(id)

                if (articleToModify == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                print("${id}번 게시물 새 제목 : ")
                articleToModify.title = readLineTrim()
                print("${id}번 게시물 새 내용 : ")
                articleToModify.body = readLineTrim()
                articleToModify.updateDate = Util.getNowDateStr()

                println("${id}번 게시물을 수정하였습니다.")
            }
            command.startsWith("article detail ") -> {
                val id = command.trim().split(" ")[2].toInt()

                var article = getArticleById(id)

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
            command == "article write" -> {
                print("제목 : ")
                val title = readLineTrim()
                print("내용 : ")
                val body = readLineTrim()

                val id = addArticle(title, body)

                println("${id}번 게시물이 작성되었습니다.")
            }
            command == "article list" -> {
                println("번호 / 작성날짜 / 제목")

                for (article in articles.reversed()) {
                    println("${article.id} / ${article.regDate} / ${article.title}")
                }
            }
            command.startsWith("article list") && command.trim().split(" ").size == 3 -> {

                if (command.trim().split(" ")[2].toInt() * 10 >= articles.size + 10) {
                    println("존재하지 않는 페이지입니다.")
                    println(command.trim().split(" ")[2].toInt() * 10)
                    println(articles.size + 10)
                    continue
                }else {
                    val pageNum = command.trim().split(" ")[2].toInt()

                    val startIndex = (pageNum * 10) -10
                    var endIndex = pageNum * 10
                    if(pageNum * 10 > articles.size) {
                        endIndex = articles.size
                    }

                    var list = articles.reversed()

                    println("번호 / 작성날짜 / 제목")
                    for (x in startIndex until endIndex) {
                        println("${list[x].id} / ${list[x].regDate} / ${list[x].title}")
                    }
                }

            }

            // 명령어가 article list로 시작하고 명령어의 단어 갯수가 3개를 넘어가는지 확인한다.
            command.startsWith("article list") && command.trim().split(" ").size > 3 -> {

                // 페이지 부분을 확인하고 해당 페이지가 존재할 수 있는 페이지인지 확인한다.
                if (command.trim().split(" ")[command.trim().split(" ").size -1].toInt() * 10 > articles.size + 10) {
                    println("존재하지 않는 페이지입니다.")
                    continue

                }else {

                    var list = articles.reversed()

                    var searchList = mutableListOf<Article>()

                    var keyWord = ""

                    for (x in 2 until command.trim().split(" ").size -1) {
                        keyWord += command.trim().split(" ")[x]
                    }

                    println("${keyWord}")

                    println("\n검색어 ${keyWord}에 따른 검색 결과 중 ${command.trim().split(" ")[command.trim().split(" ").size -1].toInt()}페이지입니다.\n")


                    for (x in 0 until list.size) {
                        if (list[x].title.contains(keyWord)) {
                            searchList.add(list[x])
                        }
                    }

                    val pageNum = command.trim().split(" ")[command.trim().split(" ").size -1].toInt()

                    val startIndex = (pageNum * 10) - 10
                    var endIndex = pageNum * 10

                    if (pageNum * 10 > searchList.size) {
                        endIndex = searchList.size
                    }

                    println("번호 / 작성날짜 / 제목")
                    for (x in startIndex until endIndex) {
                        println("${searchList[x].id} / ${searchList[x].regDate} / ${searchList[x].title}")
                    }

                }
//                else ->
//                    val pageNum = command.trim().split(" ")[2].toInt()
//
//                    val startIndex = (pageNum * 10) -10
//                    var endIndex = pageNum * 10
//                    if(pageNum * 10 > articles.size) {
//                        endIndex = articles.size
//                    }
//
//                    var list = articles.reversed()
//
//                    println("번호 / 작성날짜 / 제목")
//                    for (x in startIndex until endIndex) {
//                        println("${list[x].id} / ${list[x].regDate} / ${list[x].title}")
//                    }
//                }

            }


            else -> {
                println("`$command` 은(는) 존재하지 않는 명령어 입니다.")
            }
        }
    }

    println("== 게시판 관리 프로그램 끝 ==")
}

/* 게시물 관련 시작 */
// 가장 마지막에 입력된 게시물 번호
var articlesLastId = 0

val articles = mutableListOf<Article>()

fun getArticleById(id: Int): Article? {
    for (article in articles) {
        if (article.id == id) {
            return article
        }
    }

    return null
}

fun addArticle(title: String, body: String): Int {
    val id = articlesLastId + 1
    val regDate = Util.getNowDateStr()
    val updateDate = Util.getNowDateStr()

    val article = Article(id, regDate, updateDate, title, body)
    articles.add(article)

    articlesLastId = id

    return id
}

fun makeTestArticles() {
    for (id in 1..103) {
        val title = "제목$id"
        val body = "내용$id"

        addArticle(title, body)
    }
}

data class Article(
    val id: Int,
    val regDate: String,
    var updateDate: String,
    var title: String,
    var body: String
)
/* 게시물 관련 끝 */

/* 유틸관련 시작 */
fun readLineTrim() = readLine()!!.trim()

object Util {
    fun getNowDateStr(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return dateFormat.format(System.currentTimeMillis())
    }
}
/* 유틸관련 끝 */