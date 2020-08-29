package by.it.academy.kotlinexample

import android.util.Log

open class UserInfo(
        var name: String = "DEFAULT NAME",
        protected val email: String,
        internal val phone: String,
        surname: String,
) {

    var age: Int = 0
        private set(value) {
            if (value >= 18) {
                field = value
            }
        }
        get() {
            if (field == 19) {
                field
            }
            throw IllegalStateException("")
        }


    private lateinit var fullName: String
    private val address by lazy { "Address" }

    constructor() : this("", "", "", "")

    constructor(name: String) : this(name, "", "", name) {
        Log.d("KOTLIN_TEST", "Secondary constructor")
    }

    fun foo(param: String, param2: String = "asdasd"): String {
        age = 17
        fullName = ""
        address.trim()
        if (name.isNotEmpty()) {
            return name
        } else {
            return ""
        }
    }

    fun isValidName() = name.isNotEmpty()

    fun canCallPhone() = if (phone.length >= 6) {
        true
    } else {
        false
    }

}