package by.it.academy.kotlinexample

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.math.BigInteger
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val str: String = "-1000 USD"
        val isNegative = str.isNegative()

        val a = 9
        val b = 9
        var result = if (flag) {
            a + b
        } else if (a > b) {
            a * b
        } else {
            a - b
        }

        when (a) {
            1 -> {
                val text = "SINGLE $a"
                Log.d("RESULT", text)
            }
            2 -> Log.d("RESULT", "Double")
            else -> Log.d("RESULT", "No result")
        }

        val strA = "asdasd"

        val result1: Int?
        result1 = when {
            a > 0 -> abs(a)
            a < 0 -> a * 8
            else -> null
        }

        val result1Text = result1?.toBigInteger() ?: BigInteger.ONE

        Log.d("RESULT", result.toString())

        var counter = 0
        while (counter < 10) {
            counter++
        }

        val s = 10

        for (i in 0..s) {

        }

        val arr: Array<Int> = arrayOf(11, 44, 44, 8)


        val mutableList = mutableListOf<Int>(3, 576, 8, 87)
        mutableList.add(90)

        val immutableList = listOf(1, 5)

        val strings = immutableList
                .filter { number -> number >= 0 }
                .map { it.toString() }
                .toList()


        val userInfo = UserInfo("name", "email", "phone", "surname")
        userInfo.name = "NAME"
//        userInfo.age = 19
        val age = userInfo.age


        val info = UserInfo(
                phone = "PHONE",
                name = "NAME",
                surname = "SURNAME",
                email = "EMAIL"
        )

        info.foo(param2 = "asd", param = "asdasdasd")

        val personName = "NAME"
        val personAddress = "Address"

//                val name = userInfo.name
        // this & it
        val person: Person? = Person().apply {
            name = personName
            address = personAddress
        }

//        with(viewSettings) {
//            view1.text = text
//            view2.hint = hint
//
//            person?.run { foo(name, address) }
//        }
        person?.let {
            foo(it.name, it.address)
        }
        person?.run { foo(name, address) }

        val viewSettings = ViewSettings(
                color = Color.BLACK,
                text = "Txt",
                hint = "Hint"
        )
//        person.also {
//            it.name = personName.plus("asdlkasjdlkjasdkljaklsdja")
//        }
    }

    fun foo(name: String, address: String) {}

}
