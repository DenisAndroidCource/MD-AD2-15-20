package by.it.academy.kotlinexample.ex

import java.util.*

sealed class ResultData {

    abstract val backgroundColor: Int

    fun foo(){}

    class SuccessData(
        val result: Int,
        override val backgroundColor: Int
    ):ResultData()

    class SuccessServerData(
        val result: Int,
        val date: Date,
        override val backgroundColor: Int
    ):ResultData()

    class ErrorData(
        val errorMessage: String,
        override val backgroundColor: Int
    ):ResultData()
}