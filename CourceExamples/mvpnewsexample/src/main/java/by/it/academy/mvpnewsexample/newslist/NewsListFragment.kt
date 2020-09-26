package by.it.academy.mvpnewsexample.newslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.mvpnewsexample.NewsActionFlowController
import by.it.academy.mvpnewsexample.R
import by.it.academy.mvpnewsexample.newslist.presenter.NewsItemListModelMapper
import by.it.academy.mvpnewsexample.newslist.presenter.NewsListPresenter
import by.it.academy.mvpnewsexample.newslist.presenter.NewsListPresenterImpl
import by.it.academy.mvpnewsexample.newslist.presenter.NewsListView
import by.it.academy.mvpnewsexample.repo.NewRepositoryImpl
import by.it.academy.mvpnewsexample.repo.NewsDataModelMapper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_news_list.viewNewsList
import kotlinx.android.synthetic.main.item_news.view.viewTitleText
import kotlinx.android.synthetic.main.item_news.view.viewDescriptionText
import kotlinx.android.synthetic.main.item_news.view.viewImagePreview
import okhttp3.OkHttpClient

class NewsListFragment : Fragment(), NewsListView {

    private var newsActionFlowController: NewsActionFlowController? = null

    private val presenter: NewsListPresenter = NewsListPresenterImpl(
        newsListView = this,
        newsRepository = NewRepositoryImpl(OkHttpClient(), NewsDataModelMapper()),
        newsItemListModelMapper = NewsItemListModelMapper()
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewsActionFlowController) {
            newsActionFlowController = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        newsActionFlowController = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_news_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initItemList()
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchNewsList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    private fun initItemList() {
        viewNewsList.apply {
            adapter = NewsListAdapter { url -> newsActionFlowController?.displayNews(url) }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun showNewsList(newsList: List<NewsItemListModel>) {
        (viewNewsList.adapter as? NewsListAdapter)?.updateItemList(newsList)
    }

    override fun onError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    class NewsListAdapter(
        private val actionEvent: (String) -> Unit
    ) : RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder>() {

        private val itemList = mutableListOf<NewsItemListModel>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NewsListItemViewHolder(
                itemView = parent.run { LayoutInflater.from(context).inflate(R.layout.item_news, this, false) },
                actionEvent = actionEvent
            )

        override fun getItemCount() = itemList.size

        override fun onBindViewHolder(holder: NewsListItemViewHolder, position: Int) {
            holder.bind(itemList[position])
        }

        fun updateItemList(itemListIn: List<NewsItemListModel>) {
            itemList.apply {
                clear()
                addAll(itemListIn)
            }
            notifyDataSetChanged()
        }

        class NewsListItemViewHolder(
            itemView: View,
            private val actionEvent: (String) -> Unit
        ) : RecyclerView.ViewHolder(itemView) {

            fun bind(newsItemListModel: NewsItemListModel) {
                with(newsItemListModel) {
                    itemView.apply {
                        viewTitleText.text = title
                        viewDescriptionText.text = description
                        setOnClickListener { actionEvent(url) }
                    }

                    Glide.with(itemView.context)
                        .load(urlToImage)
                        .into(itemView.viewImagePreview)
                }
            }
        }
    }

    companion object {
        const val TAG = "NewListFragment"

        fun newInstance() = NewsListFragment()
    }
}