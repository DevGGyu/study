package ch03

class Task(val name: String, _priority: Int = DEFAULT_PRIORITY) {

    // 컴파일 타임 상수
    companion object {
        const val MIN_PRIORITY = 1
        const val MAX_PRIORITY = 5
        const val DEFAULT_PRIORITY = 3
    }

    // 사용자 정의 설정자(setter)를 사용하는 속성
    var priority = validPriority(_priority)
        set(value) {
            field = validPriority(value)
        }

    // private 검증 함수
    private fun validPriority(p: Int) =
        p.coerceIn(MIN_PRIORITY, MAX_PRIORITY)
}

fun main() {
    var myTask = Task("Kim").apply { priority = 4 }
    println(myTask.name + " " + myTask.priority)
}