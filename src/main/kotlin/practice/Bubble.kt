package practice

fun main() {
    
    // 숫자가 담긴 MutableList 생성
    print("숫자 입력(띄어쓰기): ")
    var arr = readLine()!!.split(" ").map{it.toInt()}.toMutableList()

    // 오름차순 정렬
    // 배열의 길이만큼 반복
    for (x in 0 until arr.size) {

        // 인덱스 최대값이 배열의 길이보다 1 작으므로 -1, 반복 회수는 -x
        for (y in 0 until arr.size - 1 - x) {

            // 인덱스 y와 인덱스 y의 오른쪽 인덱스와 비교하고
            // 큰 수가 뒤로 가게 해준다.
            if (arr[y] > arr[y+1]) {
                var temp = arr[y+1]
                arr[y+1] = arr[y]
                arr[y] = temp
            }
        }
    }

    // 출력
    for (x in arr.indices) {
        println(arr[x])
    }
}

class Bubble {
}