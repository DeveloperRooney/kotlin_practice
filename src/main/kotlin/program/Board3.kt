package program

fun main() {


    println("=== 게시판 프로그램 시작 ===")

    while (true) {

        print("명령어 입력 : ")
        val command = readLine()!!.trim()
        val commandBits = command.split("?", limit = 2)
        val url = commandBits[0]
        val paramStr =commandBits[1]
        val paramMap = mutableMapOf<String, String>()

        val paramStrBits = paramStr.split("&")

        for (bit in paramStrBits) {
            val paramStrBitBits = bit.split("=", limit=2)
            val key = paramStrBitBits[0]
            val value = paramStrBitBits[1]

            paramMap[key] = value
        }

        when (url) {

            "system/exit" -> {
                println("프로그램을 종료합니다.")
                break
            }

            "article/detail" -> {
                val id = paramMap["id"]!!.toInt()

                println("${id}번 게시물을 선택하였습니다.")
            }
        }

    }

    println("=== 게시판 프로그램 종료 ===")

}