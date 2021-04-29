package article

fun main() {

    println("=== 게시판 관리 프로그램 시작 ===")



}


//게시물 관련

data class Board(
    var id : Int,
    var regDate : String,
    var updateDate : String,
    var title : String,
    var contents : String
)



