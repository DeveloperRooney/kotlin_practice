package grammar


fun main() {

    val command = "ABC:::DEF:GHI:JKL:NMO"

    val commandBits = command.split(":", limit = 2)

    for (commandBitBits in commandBits) {
        println(commandBitBits)
    }

}

class Split {
}