4장 프로그의 흐름 제어
=================

### 조건문
```kotlin
if (조건식) {
    수행할 문장
}

var max: Int
if (a > b) {
    max = a
} else {
    max = b
}

val max = if (a > b) a else b
```

> IfCondition.kt

### else if
```kotlin
val number = 0
val result = if (number > 0)
        "양수 값"
    else if (number < 0)
        "음수 값"
    else
        "0"
```

> IfElseIfCondition.kt

### 비교 연산자와 논리 연산자의 복합
&&, ||, >=, <=

### 범위(range) 연산자
score in 80..89

### 인자를 사용하는 when문
```kotlin
when (인자) {
    any1 -> {}
    any2 -> {}
    else -> {}
}

when (x) {
    0, 1 -> println()
    else -> println()
}

when (x) {
    parseInt(s) -> println()
    else -> println()
}

when (x) {
    in 1..10 -> println()
    !in 10..20 -> println()
    else -> println()
}

val str = "안녕하세요"
val result = when(str) {
    is String -> "문자열입니다"
    else -> false
}
```

> WhenArgs.kt

### 인자가 없는 when
```kotlin
when {
    조건 혹은 표현식 -> println()
}
```

> WhenNoArgs.kt

### 다양한 자료형의 인자 받기

> WhenAnyCase.kt

### for 문
- for (요소 변수 in 컬렉션 혹은 범위) { 반복할 본문 }

```kotlin
for (x in 1..5) {
    println(x)
}

for (x in 1..5) println(x)
```

> ForSum.kt

### 하행, 상행 및 다양한 반복 방법
- 하행 반복 - downTo
```kotlin
for (i in 5 downTo 1) println(i) 
```
- 필요한 단계 증가 - step
```kotlin
for (i in 1..5 step 2) println(i)
```

> ForOddSum.kt

- 혼합 사용 가능
```kotlin
for (i in 5 downTo 1 step 2) println(i)
```

#### 의사 코드 (Pseudo-code)
```kotlin
n: 줄 수 입력
반복 (line: 1->n만큼) {
    반복 (space: 1 -> (n-line)만큼) { 공백 출력 }
    반복 (star: 1 -> (2*line-1)만큼) { 별표 출력 }
    개행
}
```

> ForTriangle.kt

### while문
```kotlin
var i = 1
while (i <= 5) {
    println("$i")
    ++i
}

데몬 프로그램의 사용 예
while (true) {
    temp = 온도 검사
    if (temp > 한계 온도) { 경고 발생 }
}
```

> WhileFactorial.kt

### do~while문
> DoWhileLoop.kt

