package by.it.academy.mvpnewsexample.newslist.presenter

import by.it.academy.mvpnewsexample.newslist.NewsItemListModel
import by.it.academy.mvpnewsexample.repo.NewsDataModel

class NewsItemListModelMapper: (List<NewsDataModel>) -> List<NewsItemListModel> {

    override fun invoke(newsDataModelList: List<NewsDataModel>): List<NewsItemListModel> =
        newsDataModelList.map { newsDataModel ->
            NewsItemListModel(
                title = newsDataModel.title,
                description = newsDataModel.description,
                url = newsDataModel.url,
                urlToImage = newsDataModel.urlToImage
            )
        }
}