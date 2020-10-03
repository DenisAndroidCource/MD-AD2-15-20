package by.it.academy.mvvmexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VmFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return NewsListViewModel(0) as T
    }
}