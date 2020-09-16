package sealed_classes

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