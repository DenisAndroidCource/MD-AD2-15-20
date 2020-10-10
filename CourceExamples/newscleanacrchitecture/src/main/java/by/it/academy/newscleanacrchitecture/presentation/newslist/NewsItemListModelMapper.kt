package by.it.academy.newscleanacrchitecture.presentation.newslist

import by.it.academy.newscleanacrchitecture.domain.NewsDomainModel

class NewsItemListModelMapper : (List<NewsDomainModel>) -> List<NewsItemListModel> {

    override fun invoke(newsDataModelList: List<NewsDomainModel>): List<NewsItemListModel> =
        newsDataModelList.map { newsDataModel ->
            NewsItemListModel(
                title = newsDataModel.title,
                description = newsDataModel.description,
                url = newsDataModel.url,
                urlToImage = newsDataModel.urlToImage
            )
        }
}