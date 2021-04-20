fun main() {

    var arr = Array<Int?>(5) {null}


    // indices를 통해 .. 혹은 until을 이용할 필요 없이 아이템의 최소 인덱스부터 최대 인덱스까지 출력
    for (x in arr.indices) {
        print("숫자 입력 : ")
        var num = readLine()!!.trim().toInt()
        arr[x] = num

    }

    for (x in arr.indices) {
        println(arr[x])
    }

}

class Array {
}