package by.it.academy.newtworkexample.repo

import io.reactivex.Single

interface NewsRepository {
    fun getNewsTopHeadlines(country: String): Single<List<NewsDataModel>>
}