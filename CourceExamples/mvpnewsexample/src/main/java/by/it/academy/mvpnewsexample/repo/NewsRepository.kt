package by.it.academy.mvpnewsexample.repo

import io.reactivex.Single

interface NewsRepository {
    fun getNewsTopHeadlines(country: String): Single<List<NewsDataModel>>
}