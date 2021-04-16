fun main() {


    println("=== 프로그램 시작 ====")

    val numbers = IntArray(3) {0}

    for (x in numbers.indices) {
        print("숫자 입력 : ")
        var num = readLine()!!.trim().toInt()

        numbers[x] = num
    }

//    for (x in numbers.indices) {
//        println("${x+1}번째 입력한 숫자 : ${numbers[x]}")
//    }

    var cnt = 0
    for (number in numbers) {
        cnt++
        println("${cnt}번째 입력한 숫자 : ${number}")
    }


    println("=== 프로그램 종료 ====")
}

class ArrPractice {
}