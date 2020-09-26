package by.it.academy.mvpnewsexample.newslist.presenter

import by.it.academy.mvpnewsexample.newslist.NewsItemListModel

interface NewsListView {
    fun showNewsList(newsList: List<NewsItemListModel>)
    fun onError(errorMessage: String)
}