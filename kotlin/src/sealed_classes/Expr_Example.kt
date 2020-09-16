package sealed_classes

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}

fun strMonth(month: Month): String = when(month) {
    is Month.January -> "1월"
    is Month.February -> "2월"
    is Month.March -> "3월"
    is Month.April -> "4월"
    is Month.May -> "5월"
    is Month.June -> "6월"
    is Month.July -> "7월"
    is Month.August -> "8월"
    is Month.September -> "9월"
    is Month.October -> "10월"
    is Month.November -> "11월"
    is Month.December -> "12월"
}

fun main() {
    eval(Sum(Const(2.0), Const(2.0)))
    strMonth(Month.January(1, "Jan"))
}