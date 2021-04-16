fun main() {
    val human1 = Human();

    human1.name = "철수";
    human1.gender = "남자";
    human1.loc = "철원";
    human1.age = 23;


    val human2 = Human();
    human2.name = "영희";
    human2.gender = "여자";
    human2.loc = "남원";
    human2.age = 22;

    val human3 = Human();
    human3.name = "영수";
    human3.gender = "남자";
    human3.loc = "진천";
    human3.age = 25;


    println("첫번째 사람은 ${human1.name} / ${human1.gender} / ${human1.age} / ${human1.loc}")
    println("두번째 사람은 ${human2.name} / ${human2.gender} / ${human2.age} / ${human2.loc}")
    println("세번째 사람은 ${human3.name} / ${human3.gender} / ${human3.age} / ${human3.loc}")

    println("============ toString() 오버라이딩 ==================")

    println(human2.introduceMsg())
}

class Human {
    var name:String = "";
    var gender:String = "";
    var age:Int = 0;
    var loc:String = "";

    fun println(){
        println("${name} / ${this.gender} / ${this.age} / ${this.loc}")

    }

    fun introduceMsg() : String = "안녕하세요. 저는 ${age}살, ${loc}출신 ${name}입니다. 성별 : ${gender}"

    override fun toString() = "name=$name, gender=$gender, age=$age, loc=$loc"


}