package by.it.academy.newscleanacrchitecture.datasource

import by.it.academy.newscleanacrchitecture.data.NewsDataModel
import io.reactivex.Single

interface NewsDataSource {
    fun getNewsTopHeadlines(country: String): Single<List<NewsDataModel>>
}