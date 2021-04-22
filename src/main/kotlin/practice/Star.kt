package practice

fun main() {

    var num = readLine()!!.trim().toInt()

    for (x in 1..num) {
        for (y in 1..x) {
            print("*")
        }
        println()
    }
}

class Star {
}