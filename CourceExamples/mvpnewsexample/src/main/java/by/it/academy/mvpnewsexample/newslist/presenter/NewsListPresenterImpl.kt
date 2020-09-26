package by.it.academy.mvpnewsexample.newslist.presenter

import by.it.academy.mvpnewsexample.newslist.NewsItemListModel
import by.it.academy.mvpnewsexample.repo.NewsDataModel
import by.it.academy.mvpnewsexample.repo.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class NewsListPresenterImpl(
    private var newsListView: NewsListView?,
    private val newsRepository: NewsRepository,
    private val newsItemListModelMapper: (List<NewsDataModel>) -> List<NewsItemListModel>
) : NewsListPresenter {

    private var disposable: Disposable? = null

    override fun fetchNewsList() {
        disposable = newsRepository.getNewsTopHeadlines("us")
            .map { newsItemListModelMapper(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> newsListView?.showNewsList(list) },
                { throwable -> newsListView?.onError(throwable.toString()) }
            )
    }

    override fun dispose() {
        disposable?.dispose()
        newsListView = null
    }
}