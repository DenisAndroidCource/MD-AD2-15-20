package by.it.academy.dependencyinjection

import android.util.Log

class AllNewsRepoImpl {
    override fun getAllNews(): List<News> {
        Log.d("NewsRepoImpl", "AllNewsRepoImpl->getAllNews")
        return emptyList()
    }
}