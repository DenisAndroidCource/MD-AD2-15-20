package by.it.academy.newscleanacrchitecture.presentation.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.it.academy.newscleanacrchitecture.domain.NewsDomainModel
import by.it.academy.newscleanacrchitecture.domain.NewsTopHeadlinesUseCase
import by.it.academy.newscleanacrchitecture.domain.NewsTopHeadlinesUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsListViewModel : ViewModel() {

    private val newsListViewModelMapper: (List<NewsDomainModel>) -> List<NewsItemListModel> = NewsItemListModelMapper()
    private val newsTopHeadlinesUseCase: NewsTopHeadlinesUseCase = NewsTopHeadlinesUseCaseImpl()
    private var disposable: Disposable? = null

    private val mutableNewsLiveData = MutableLiveData<List<NewsItemListModel>>()
    val newsLiveData: LiveData<List<NewsItemListModel>> = mutableNewsLiveData

    private val mutableNewsErrorLiveData = MutableLiveData<Throwable>()
    val newsErrorLiveData: LiveData<Throwable> = mutableNewsErrorLiveData

    public fun fetchTopHeadlineNews() {
        disposable = newsTopHeadlinesUseCase.getNewsTopHeadlines("us")
            .subscribeOn(Schedulers.computation())
            .map { domainModelList -> newsListViewModelMapper(domainModelList) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {itemList -> mutableNewsLiveData.value = itemList },
                {t -> mutableNewsErrorLiveData.value = t }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}