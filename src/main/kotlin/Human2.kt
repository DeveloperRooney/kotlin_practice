// Human.kt를 변형하여 데이터 클래스 만들고 사용

fun main() {
    val human1 = Human2(1,"수한", "남자", 29, "대전")
    val human2 = Human2(2,"대근", "남자", 29, "대전")
    val human3 = Human2(3,"대건", "남자", 27, "대전")

    print(human1)
    print(human2)
    print(human3)

}

// 기본 생성자 입력
// toString 오버라이딩 안 해도 됨
data class Human2(var id:Int, var name:String, var gender:String, var age:Int, var loc:String) {

    fun println(){
        println("${name} / ${this.gender} / ${this.age} / ${this.loc}")

    }
    fun introduceMsg() : String = "안녕하세요. 저는 ${age}살, ${loc}출신 ${name}입니다. 성별 : ${gender}"

}