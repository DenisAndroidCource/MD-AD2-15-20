package by.it.academy.newscleanacrchitecture.domain

import io.reactivex.Single

interface NewsRepository {
    fun getNewsTopHeadlines(country: String): Single<List<NewsDomainModel>>
}