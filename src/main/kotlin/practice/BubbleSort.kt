package practice

fun main() {

    println("=== 프로그램 시작 ===")

    print("숫자(띄어쓰기로 구분) : ")
    val numbers = readLine()!!.split(" ").map{it.toInt()}.toMutableList()



    println("오름차순 정렬 결과 : ")
    bubbleSort(numbers)

    printNumbers(numbers)

    println("내림차순 정렬 결과 : ")
    printNumbersReversed(numbers)


//    for (number in numbers) {
//        println(number)
//    }

//    numbers.withIndex().forEach {
//        println("${it.index} : ${it.value}")
//    }


    println("=== 프로그램 종료 ===")

}


// 오름차순
fun printNumbers(numbers : MutableList<Int>) {

    for (x in 0 until numbers.size) {
        for (y in 0 until numbers.size - 1) {
            if (numbers[y] > numbers[y+1]) {
                var temp = numbers[y+1]
                numbers[y+1] = numbers[y]
                numbers[y] = temp
            }
        }
    }

    numbers.withIndex().forEach {
        print("${it}")
    }
    println()
}

// 내림차순
fun printNumbersReversed(numbers : MutableList<Int>) {
    numbers.withIndex().reversed().forEach {
        print("${it.value}")
    }
    println()
}

fun bubbleSort(numbers : MutableList<Int>) {

    var maxDept = numbers.size - 1
    // 4
    // 0, 1, 2, 3


    var raiseCnt = 1

    for (depth in maxDept downTo 1) {
        println("raiseCnt : ${raiseCnt++}")
        for (x in 0 until depth) {
            println("numbers[${x}] vs number[${x+1}]")

            if (numbers[x] > numbers[x + 1]) {
                numbers[x] = numbers[x + 1].also { numbers[x + 1] = numbers[x]}
            }
        }
    }
}

class Sort {
}