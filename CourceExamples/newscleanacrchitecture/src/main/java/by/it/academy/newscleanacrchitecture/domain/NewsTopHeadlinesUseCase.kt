package by.it.academy.newscleanacrchitecture.domain

import io.reactivex.Single

interface NewsTopHeadlinesUseCase {
    fun getNewsTopHeadlines(country: String): Single<List<NewsDomainModel>>
}