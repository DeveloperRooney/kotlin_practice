package practice

fun main() {

    var arr = readLine()!!.split(" ").map{it.toInt()}.toMutableList()

    for (x in 0 until arr.size) {
        for (y in 0 until arr.size - x - 1) {
            if (arr[y] > arr[y+1]) {
                var temp = arr[y+1]
                arr[y+1] = arr[y]
                arr[y] = temp
            }
        }
    }

    for (index in arr.indices) {
        println(arr[index])
    }
}

class Bubble {
}