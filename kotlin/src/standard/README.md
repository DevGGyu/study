### 범위 지정 함수 : let, run, with, apply, also

> 5개의 함수는 전달받는 인자와 작동 방식, 결과가 매우 비숫하여 서로를 대체해서 사용할 수 있습니다.
- 두가지 요소
```
수신 객체
수신 객체 지정 람다
```

#### 1. run, with, apply -> this
- 호출 시 수신 객체 입력
```
receiver로 암시적 전달 : apply, run
parameter로 명시적 전달 : with
```
- 코드 블록으로 수신 객체 전달
```
receiver로 암시적 전달
```
- 코드 형태
> run : 리턴 값은 원하는 타입으로 줄 수 있다.
```kotlin
public inline fun <T, R> T.run(block: T.() -> R): R {
    return block()
}

Account("오픈뱅킹", 1234567890).run {
    println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
    println("bankName: $bankName, accountNumber: $accountNumber")
}

val result = Account("오픈뱅킹", 1234567890).run {
    println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
    println("bankName: $bankName, accountNumber: $accountNumber")
    "success"
}
println("result : $result")
```
```
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
result : success
```

> apply : 리턴 값은 자기 자신이다.
```kotlin
public inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

Account("오픈뱅킹", 1234567890).apply {
    println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
    println("bankName: $bankName, accountNumber: $accountNumber")
}

val result2 = Account("오픈뱅킹", 1234567890).apply {
    println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
    println("bankName: $bankName, accountNumber: $accountNumber")
}
println("result2: $result2")
```
```
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
result2: Account(bankName=오픈뱅킹, accountNumber=1234567890)
```

> with : 리턴 값은 원하는 타입으로 줄 수 있다.
```kotlin
public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    return receiver.block()
}

with(Account("오픈뱅킹", 1234567890)) {
    println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
    println("bankName: $bankName, accountNumber: $accountNumber")
}

val result3 = with(Account("오픈뱅킹", 1234567890)) {
    println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
    println("bankName: $bankName, accountNumber: $accountNumber")
    "success"
}
println("result3 : $result3")
```
```
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
result3 : success
```

#### 2. also, let -> it
- 호출 시 수신 객체 입력
```
receiver로 암시적 전달 : also, let
```
- 코드 블록으로 수신 객체 전달
```
parameter로 명시적 전달
```
- 코드 형태

> also : 리턴 값은 자기 자신이다.
```kotlin
public inline fun <T> T.also(block: (T) -> Unit): T {
    block(this)
    return this
}

Account("오픈뱅킹", 1234567890).also {
    println("bankName: ${it.bankName}, accountNumber: ${it.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
}

val result4 = Account("오픈뱅킹", 1234567890).also {
    println("bankName: ${it.bankName}, accountNumber: ${it.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
}
println("result4 : $result4")
```
```
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
result4 : Account(bankName=오픈뱅킹, accountNumber=1234567890)
```

> let : 리턴 값은 원하는 값으로 줄 수 있다.
```kotlin
public inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}

Account("오픈뱅킹", 1234567890).let {
    println("bankName: ${it.bankName}, accountNumber: ${it.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
}

val result5 = Account("오픈뱅킹", 1234567890).let {
    println("bankName: ${it.bankName}, accountNumber: ${it.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
    "success"
}
println("result5 : $result5")
```
```
bankName: 오픈뱅킹, accountNumber: 1234567890
bankName: 오픈뱅킹, accountNumber: 1234567890
result5 : success
```

#### 3. 언제 사용해야 할까?
- run
> 어떤 값을 계산<br>
> 여러개의 지역 변수의 범위 제한<br>
> 매개 변수로 전달된 명시적 수신객체를 암시적 수신 객체로 변환 할 때

- apply
> 수신 객체 람다 내부에서 수신 객체의 함수를 사용하지 않고 자신을 다시 반환 하려는 경우에 사용한다.<br>
> 예 : 객체의 초기화

- with
> Null이 아닌 객체가 수신 객체<br>
> 결과가 필요하지 않는 경우

- also
> 수신 객체 람다가 전달된 수신 객체를 전혀 사용 하지 않을 때<br>
> 수신 객체의 속성을 변경하지 않을 때<br>
> apply 처럼 수신 객체 반환<br>
> 예 : 데이터 유효성 검사

- let
> 지정된 값이 null이 아닌 경우에 코드를 실행해야 하는 경우<br>
> Nullable 객체를 다른 Nullable 객체로 변환하는 경우 <br>
> 단일 지역 변수의 번위를 제한 하는 경우

#### 주의
> 범위 지정 함수는 중첩이 가능한데, 가독성이 떨어지고 파악하기 어려워지기 때문에, 원칙적으로는 중첩은 하지 않는 것이 좋다.<br>
> 특히 암시적으로 전달되는 apply, run, with 는 중첩하지 말 것. (나중에 혼동 됨)<br>
> also, let 을 중첩 할 때는 it을 쓰지 말고, 명시적인 이름을 사용한다.

- 호출 체인 + 범위 지정 함수 = 가독성 향상
```kotlin
StringBuilder().apply {
    append("Hello")
    append("World")
}.also {
    println("helloWorld : $it")
}
// helloWorld : HelloWorld
```

#### 4. takeIf, takeUnless

- takeIf
> 코드 형태는 if문 처럼 생겼음
```kotlin
public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? {
    return if (predicate(this)) this else null
}

val input = "Kotlin"
val keyword = "in"

val index = input.indexOf(keyword).takeIf { it >= 0 } ?: error("keyword not found")

println("'$keyword' was found in '$input'")
println(input)
println(" ".repeat(index) + "^")
```
```
[success]
'in' was found in 'Kotlin'
Kotlin
    ^

[error]
Exception in thread "main" java.lang.IllegalStateException: keyword not found
	at standard.MainKt.main(Main.kt:100)
	at standard.MainKt.main(Main.kt)
```

- takeUnless
> takeIf의 반대
```kotlin
public inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T? {
    return if (!predicate(this)) this else null
}

val input2 = "Kotlin"
val keyword2 = "in"

val index2 = input2.indexOf(keyword2).takeUnless { it < 0 } ?: error("keyword2 not found")

println("'$keyword2' was found in '$input2'")
println(input2)
println(" ".repeat(index2) + "^")
```
```
[success]
'in' was found in 'Kotlin'
Kotlin
    ^

[error]
Exception in thread "main" java.lang.IllegalStateException: keyword not found
	at standard.MainKt.main(Main.kt:100)
	at standard.MainKt.main(Main.kt)
```

#### 주의
> 안전한 호출 연산자 ?. 사용을 잘 하도록 하자