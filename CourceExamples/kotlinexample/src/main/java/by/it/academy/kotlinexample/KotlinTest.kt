package by.it.academy.kotlinexample

import android.util.Log

fun foo() {
    val intVal = 89
    val boolVal: Boolean
    val floaVal: Float
    val doubleVal: Double
    val charleVal: Char

    val typeVariable = true

    val variableMutable = 20

    var variable = 20
    variable = 44

    """asdasdasda
        |                   ds""".trimMargin()

    val str: String = "asdjalksdjaksjdkajsa"+
            "asdasd"
    var strTemp = str.plus("asdhaksjdad")
    val strResult = strTemp.format()
    strTemp.toInt()
}

fun logActiveUsers(userAmount: Int) {
    Log.d("logActiveUsers", "User amount ${userAmount.minus(10)} of active users")
}