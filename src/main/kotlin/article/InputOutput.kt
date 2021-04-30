package article

import java.io.*
import java.nio.file.Files

fun main() {

    val path = "C:\\Users\\SBS-\\IdeaProjects\\file\\article\\exam.txt"

    val text = "예제 작성입니다"

    val fileTree : FileTreeWalk = File("C:\\Users\\SBS-\\IdeaProjects\\file\\article").walk()


    var cnt = 0
    for (file in fileTree) {
        if (file.name.contains("txt")) {
            cnt++
        }
    }

    print(cnt)


}

class InputOutput {
}