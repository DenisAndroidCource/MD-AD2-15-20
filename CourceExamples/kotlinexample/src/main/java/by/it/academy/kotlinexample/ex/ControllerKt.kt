package by.it.academy.kotlinexample.ex

import by.it.academy.kotlinexample.ObjExample
import by.it.academy.kotlinexample.ex.ResultData.ErrorData
import by.it.academy.kotlinexample.ex.ResultData.SuccessData

class ControllerKt {

    fun foo(a: Int): ResultData {
        return if (a == 0)
            SuccessData(0, 0)
        else
            ErrorData("", 0)
    }

    fun foo2() {
        when (val result = foo(0)) {
            is SuccessData -> result.result
            is ErrorData -> result.errorMessage
        }
    }

}