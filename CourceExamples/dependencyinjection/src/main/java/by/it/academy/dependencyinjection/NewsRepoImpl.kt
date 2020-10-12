package by.it.academy.dependencyinjection

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

class NewsRepoImpl @Inject constructor(): NewsRepo {
    override fun getAllNews(): List<News> {
        Log.d("NewsRepoImpl", "NewsRepoImpl->getAllNews")
        return emptyList()
    }
}