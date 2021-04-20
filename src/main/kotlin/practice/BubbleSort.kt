package practice

fun main() {

    println("=== 프로그램 시작 ===")

    print("숫자(띄어쓰기로 구분) : ")
    val numbers = readLine()!!.split(" ").map{it.toInt()}.toMutableList()


    // 오름차순
    var temp = 0
    for (x in numbers.indices) {
        for (y in 1 until numbers.size -x) {
            if (numbers[y] < numbers[y-1] ) {
                temp = numbers[y]
                numbers[y] = numbers[y-1]
                numbers[y-1] = temp

            }
        }
    }

    print("오름차순 정렬 결과 : ")
    for (x in numbers.indices) {
        print("${numbers[x]} ")
    }
    println()


    // 내림차순
    temp = 0
    for (x in numbers.indices) {
        for (y in 1 until numbers.size -x) {
            if (numbers[y] > numbers[y-1] ) {
                temp = numbers[y]
                numbers[y] = numbers[y-1]
                numbers[y-1] = temp

            }
        }
    }

    print("내림차순 정렬 결과 : ")
    for (x in numbers.indices) {
        print("${numbers[x]} ")
    }
    println()



    println("=== 프로그램 종료 ===")

}

class Sort {
}