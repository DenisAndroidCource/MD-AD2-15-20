package by.it.academy.newscleanacrchitecture.data

import by.it.academy.newscleanacrchitecture.datasource.HttpNewsDataSource
import by.it.academy.newscleanacrchitecture.datasource.NewsDataSource
import by.it.academy.newscleanacrchitecture.domain.NewsDomainModel
import by.it.academy.newscleanacrchitecture.domain.NewsRepository
import io.reactivex.schedulers.Schedulers

class NewsDataRepository : NewsRepository {

    private val newsDataSource: NewsDataSource = HttpNewsDataSource()
    private val newsDomainModelMapper: (List<NewsDataModel>) -> List<NewsDomainModel> = NewsDomainModelMapper()

    override fun getNewsTopHeadlines(country: String) =
        newsDataSource.getNewsTopHeadlines(country)
            .subscribeOn(Schedulers.computation())
            .map { dataList -> newsDomainModelMapper(dataList) }
}