fun main() {

    println("=== 프로그램 시작 ===")

    // MutableList
    val muList : MutableList<Int> = mutableListOf<Int>()


    while (true) {
        print("숫자 : ")
        var num = readLine()!!.trim().toInt()

        if (num == 0) {
            println("=== 프로그램 종료 ===")
            break
        }

        muList.add(num)
    }

    for (x in 0..muList.size-1) {
        println("입력된 숫자 : ${muList[x]}")
    }


//    입력 방식
//    print("숫자 : ")
//    var num1 = readLine()?.trim()?.toInt()
//    print("숫자 : ")
//    var num2 = readLine()!!.trim().toInt()
//    print("숫자 : ")
//    var num3 = (readLine() ?:"").trim().toInt()
//    print("숫자 : ")
//    var num4 = readLine()?.trim()?.toInt() ?:0


}

data class InputNumber(var num1 : Int, var num2 : Int, var num3 : Int){

}