package by.it.academy.kotlinappexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

// SOLID

class MainActivity : AppCompatActivity() {

    private val calculationController = CalculationController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewButtonResult.setOnClickListener {
            val result = calculationController.add(
                    numberA = viewEditTextA.text.toString().toInt(),
                    numberB = viewEditTextB.text.toString().toInt()
            )
            viewTextViewResult.text = result.toString()
        }
    }
}
