package by.it.academy.newscleanacrchitecture.domain

import by.it.academy.newscleanacrchitecture.data.NewsDataRepository
import io.reactivex.Single

class NewsTopHeadlinesUseCaseImpl : NewsTopHeadlinesUseCase {

    private val newsRepository: NewsRepository = NewsDataRepository()

    override fun getNewsTopHeadlines(country: String) =
        if (country != "ch") {
            newsRepository.getNewsTopHeadlines(country)
        } else {
            Single.error(Throwable("The call for $country is forbidden"))
        }
}