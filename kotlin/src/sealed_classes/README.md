## Sealed Classes

### enum 클래스
```kotlin
enum class Days(dayNo: Int) {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6)
}
```
- 각각의 enum 상수에 대해 하나의 인스턴스만 생성 가능 (일요일은 dayNo가 항상 0)
- 상태 변경 안됨 (월요일이 0인 곳에서 문제 생김)
- 서브 클래스 생성이 안됨

#### Sealed 클래스

- enum의 확장판 같은 느낌
```kotlin
sealed class Month {
    data class January(val monthIndex: Int, val shortForm: String) : Month()
    data class February(val monthIndex: Int, val shortForm: String, val noOfDays: Int) : Month()
    data class March(val monthIndex: Int, val shortForm: String) : Month()
    data class April(val monthIndex: Int, val shortForm: String) : Month()
    data class May(val monthIndex: Int, val shortForm: String) : Month()
    data class June(val monthIndex: Int, val shortForm: String) : Month()
    data class July(val monthIndex: Int, val shortForm: String) : Month()
    data class August(val monthIndex: Int, val shortForm: String) : Month()
    data class September(val monthIndex: Int, val shortForm: String) : Month()
    data class October(val monthIndex: Int, val shortForm: String) : Month()
    data class November(val monthIndex: Int, val shortForm: String) : Month()
    data class December(val monthIndex: Int, val shortForm: String) : Month()
}

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}
```
- 클래스 앞에 sealed 키워드를 붙여서 만든다.
- 반드시 같은 [파일] 내에 선언되어야 한다.
- 기본적으로 abstract 클래스 -> 초기화가 안됨
- private 생성자만 갖는다.
- Sealed 클래스의 서브클래스를 상속한 클래스들은 같은 파일내에 없어도 된다.
- 서브클래스에 대해 여러개의 인스턴스 생성 가능 (상태값 변경 가능, February객체 2개를 생성하고 비교 가능)
- Sealed 클래스의 계층을 생성할 수 있다.
- when 표현식에서 sealed 클래스를 사용하면 else절을 구현할 필요가 없다.