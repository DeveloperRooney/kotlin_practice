package program

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {

    println("=== 게시판 글 작성 프로그램 시작 ===")

    var idx = 0

    var boardList = mutableListOf<Board>()

    while (true) {

        print("명령어 입력 : ")
        val cmd = readLine()!!

        // 새로운 글 작성
        if (cmd== "board write") {
            println("새로운 글을 작성합니다.")

            print("제목 입력 : ")
            val subject = readLine()!!

            print("내용 입력 : ")
            val contents = readLine()!!

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")

            val reg = LocalDateTime.now()
            val regdate = reg.format(formatter)

            val edit = LocalDateTime.now()
            val editdate = edit.format(formatter)
            idx ++

            val board = Board(idx, subject, contents, regdate, editdate)

            boardList.add(board)

            println("${idx}번 글이 작성되었습니다.\n")


        // 프로그램 종료    
        }else if (cmd == "board exit") {
            println("프로그램을 종료합니다.")
            break
        
        // 글 삭제
        }else if (cmd.startsWith("board delete")) {

            if (cmd.trim().split(" ").size < 3 ) {
                println("게시글 번호를 입력하지 않았습니다.")
                continue
            }
            // 삭제할 글 번호 입력
            val num = cmd.trim().split(" ")[2].toInt()
            
            var cnt = 0
            for (x in 0 until boardList.size) {
                if (boardList[x].idx == num) {
                    boardList.removeAt(x)
                    cnt++
                    println("글이 삭제되었습니다.")
                    break

                }

            }

            if (cnt != 1) {
                println("해당 글은 존재하지 않습니다.\n")
                continue
            }


        // 게시판 글 리스트
        }else if (cmd == "board list") {
            println("=== 게시판 글 목록 ===")
            for (x in 0 until boardList.size) {
                println("${boardList[x].idx} / ${boardList[x].subject} / ${boardList[x].regdate} / ${boardList[x].editdate}")
            }
            println()
        
        // 존재하지 않는 명령어일 때
        }else if (cmd.startsWith("board modify")) {
            if (cmd.trim().split(" ").size < 3) {
                println("게시글 번호를 입력하지 않았습니다.")
                continue
            }
            var num = cmd.trim().split(" ")[2].toInt()

            var cnt = 0
            for (x in 0 until boardList.size) {
                if (boardList[x].idx == num) {
                    cnt++
                    print("제목 : ")
                    var subject = readLine()!!
                    print("내용 : ")
                    var contents = readLine()!!

                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")
                    val edit = LocalDateTime.now()
                    val editdate = edit.format(formatter)

                    boardList[x].subject = subject
                    boardList[x].contents = contents
                    boardList[x].editdate = editdate

                }
            }
            if (cnt != 1) {
                println("존재하지 않는 글입니다.")
            }
        }else if(cmd == "exit") {
            println("=== 게시판 글 작성 프로그램 종료 ===")
            break
        }else if(cmd.startsWith("board detail")) {
            if (cmd.trim().split(" ").size < 3) {
                println("게시글 번호를 입력하지 않았습니다.")
                continue
            }

            var num = cmd.trim().split(" ")[2].toInt()

            var cnt = 0
            for (x in 0 until boardList.size) {
                if (boardList[x].idx == num) {
                    cnt++

                    println("제목 : ${boardList[x].subject}")
                    println("글번호 : ${boardList[x].idx}")
                    println("작성날짜 : ${boardList[x].regdate}")
                    println("수정날짜 : ${boardList[x].editdate}")
                    println("내용 : ${boardList[x].contents}")

                }
            }
            if (cnt != 1) {
                println("존재하지 않는 글입니다.")
            }
        }else {
            println("'${cmd}'는 존재하지 않는 명령어입니다.")
        }
    }


}

data class Board (val idx : Int, var subject : String, var contents : String, val regdate : String,
                  var editdate : String)