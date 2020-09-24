package standard

data class Account(val bankName: String, val accountNumber: Int)

class User {
    var name: String? = null
    var age: Int? = null
}

fun main() {
    /**
     * run
     * */
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

    /**
     * apply
     * */
    Account("오픈뱅킹", 1234567890).apply {
        println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
        println("bankName: $bankName, accountNumber: $accountNumber")
    }

    val result2 = Account("오픈뱅킹", 1234567890).apply {
        println("bankName: ${this.bankName}, accountNumber: ${this.accountNumber}")
        println("bankName: $bankName, accountNumber: $accountNumber")
    }
    println("result2: $result2")

    /**
     * with
     * */
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

    /**
     * also
     * */
    Account("오픈뱅킹", 1234567890).also {
        println("bankName: ${it.bankName}, accountNumber: ${it.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
    }

    val result4 = Account("오픈뱅킹", 1234567890).also { account ->
        println("bankName: ${account.bankName}, accountNumber: ${account.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
    }
    println("result4 : $result4")

    /**
     * let
     * */
    Account("오픈뱅킹", 1234567890).let {
        println("bankName: ${it.bankName}, accountNumber: ${it.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
    }

    val result5 = Account("오픈뱅킹", 1234567890).let { account ->
        println("bankName: ${account.bankName}, accountNumber: ${account.accountNumber}")
//        println("bankName: $bankName, accountNumber: $accountNumber") // error
        "success"
    }
    println("result5 : $result5")

    /**
     * chain
     * */
    StringBuilder().apply {
        append("Hello")
        append("World")
    }.also {
        println("helloWorld : $it")
    }

    /**
     * takeIf
     * */
    val input = "Kotlin"
    val keyword = "in"

    val index = input.indexOf(keyword).takeIf { it >= 0 } ?: error("keyword not found")

    println("'$keyword' was found in '$input'")
    println(input)
    println(" ".repeat(index) + "^")

    /**
     * takeUnless
     * */
    val input2 = "Kotlin"
    val keyword2 = "in"

    val index2 = input2.indexOf(keyword2).takeUnless { it < 0 } ?: error("keyword2 not found")

    println("'$keyword2' was found in '$input2'")
    println(input2)
    println(" ".repeat(index2) + "^")
}