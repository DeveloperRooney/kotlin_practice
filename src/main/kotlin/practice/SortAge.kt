package practice

// 나이별로 정렬

fun main() {

    var members : MutableList<Member> = mutableListOf<Member>()

    var id = 1;

    while (true) {

        print("이름 입력 : ")
        var name = readLine()!!.trim()

        if (name.equals("0000")) {
            break
        }

        print("나이 입력 : ")
        var age = readLine()!!.trim().toInt()

        print("성별 입력 : ")
        var gender = readLine()!!.trim()

        var member : Member = Member(id, age, name, gender)

        members.add(member)

        id++
    }

    for (x in 0 until members.size) {
        println(members[x])
    }

    println("나이 오름차순 정렬")

    for (x in 0 until members.size) {
        for (y in 0 until members.size - 1 - x) {
            if (members[y].age > members[y+1].age) {
                var temp = members[y]
                members[y] = members[y+1]
                members[y+1] = temp
            }
        }
    }

    for (x in 0 until members.size) {
        println(members[x])
    }
}


data class Member(var id : Int,var age : Int, var name : String, var gender : String)
