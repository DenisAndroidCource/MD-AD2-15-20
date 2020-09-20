package by.it.academy.newtworkexample.newslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.newtworkexample.NewsActionFlowController
import by.it.academy.newtworkexample.R
import by.it.academy.newtworkexample.repo.NewRepositoryImpl
import by.it.academy.newtworkexample.repo.NewsDataModel
import by.it.academy.newtworkexample.repo.NewsDataModelMapper
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.item_news.view.*
import okhttp3.OkHttpClient

class NewsListFragment : Fragment() {

    private var newsActionFlowController: NewsActionFlowController? = null
    private var disposable: Disposable? = null

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
        fetchNewsList()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun initItemList() {
        viewNewsList.apply {
            adapter = NewsListAdapter { url -> newsActionFlowController?.displayNews(url) }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun fetchNewsList() {
        disposable = NewRepositoryImpl(
            okHttpClient = OkHttpClient(),
            newsDataModelMapper = NewsDataModelMapper()
        ).getNewsTopHeadlines("us")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> (viewNewsList.adapter as? NewsListAdapter)?.updateItemList(list) },
                { throwable -> Log.d("MainActivity", throwable.toString()) }
            )
    }

    class NewsListAdapter(
        private val actionEvent: (String) -> Unit
    ) : RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder>() {

        private val itemList = mutableListOf<NewsDataModel>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NewsListItemViewHolder(
                itemView = parent.run { LayoutInflater.from(context).inflate(R.layout.item_news, this, false) },
                actionEvent = actionEvent
            )

        override fun getItemCount() = itemList.size

        override fun onBindViewHolder(holder: NewsListItemViewHolder, position: Int) {
            holder.bind(itemList[position])
        }

        fun updateItemList(itemListIn: List<NewsDataModel>) {
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

            fun bind(newsDataModel: NewsDataModel) {
                with(newsDataModel) {
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