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
        if (cmd.equals("board write")) {
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
        }else if (cmd.equals("board exit")) {
            println("프로그램을 종료합니다.")
            break
        
        // 글 삭제
        }else if (cmd.equals("board delete")) {
            
            // 삭제할 글 번호 입력
            print("삭제할 글 번호 : ")
            var num = readLine()!!.trim().toInt()
            
            var cnt = 0
            for (x in 0 until boardList.size) {
                if (boardList[x].idx == num) {
                    boardList.removeAt(x)
                    cnt--
                    println("글이 삭제되었습니다.")
                    break

                }else {
                    cnt++
                }

            }

            if (cnt == boardList.size) {
                println("해당 글은 존재하지 않습니다.\n")
                continue
            }


        // 게시판 글 리스트
        }else if (cmd.equals("board list")) {
            println("=== 게시판 글 목록 ===")
            for (x in 0 until boardList.size) {
                println("${boardList[x].idx} / ${boardList[x].subject} / ${boardList[x].regdate} / ${boardList[x].editdate}")
            }
            println()
        
        // 존재하지 않는 명령어일 때
        }else {
            println("'${cmd}'는 존재하지 않는 명령어입니다.")
        }
    }

    println("=== 게시판 글 작성 프로그램 종료 ===")

}

data class Board (val idx : Int, val subject : String, val contents : String, val regdate : String,
val editdate : String)