fun main() {
    println("=== 프로그램 시작 ===")

    print("숫자 개수 : ")
    var num: Int = readLine()!!.trim().toInt()

    var arr = IntArray(num, { 0 })

    println("${num}개의 숫자를 입력받습니다.")
    for (x in 1..num) {
        print("${x}번째 숫자 : ")
        var inputnum: Int = readLine()!!.trim().toInt();

        arr.set(x - 1, inputnum)

    }

    println("입력이 완료되었습니다.")
    for (x in 1..arr.size) {
        println("${x}번째로 입력된 숫자 : ${arr[x - 1]}")
    }

    println("입력하신 숫자의 합은 ${arr.sum()}")

    println("=== 프로그램 종료 ===")
}

class sum {
}