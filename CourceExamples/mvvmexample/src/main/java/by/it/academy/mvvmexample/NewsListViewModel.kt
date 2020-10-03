package by.it.academy.mvvmexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsListViewModel(
    val param: Int
): ViewModel() {

    private val mutableData = MutableLiveData<Int>()
    val liveDataInt: LiveData<Int> = mutableData

    fun fetchData(){
        var i = 0
        mutableData.value = i
        mutableData.postValue(i)
    }

}