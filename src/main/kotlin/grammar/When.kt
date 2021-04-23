package grammar

fun main() {

    val number = 10

    when (number) {
        in 90..100 -> println("A")
        in 80 until 90 -> println("B")
        in 70 until 80 -> println("C")
        in 60 until 70 -> println("D")
        else -> println("F")
    }
}

class When {
}