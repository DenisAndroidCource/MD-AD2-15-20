package by.it.academy.newscleanacrchitecture.presentation.newslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.it.academy.newscleanacrchitecture.R
import by.it.academy.newscleanacrchitecture.presentation.NewsActionFlowController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListFragment : Fragment() {

    private var newsActionFlowController: NewsActionFlowController? = null
    private lateinit var viewModel: NewsListViewModel

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

        viewModel = ViewModelProvider.NewInstanceFactory().create(NewsListViewModel::class.java)
        with(viewLifecycleOwner) {
            viewModel.newsLiveData.observe(this, Observer { items ->
                (viewNewsList.adapter as? NewsListAdapter)?.updateItemList(items)
            })
            viewModel.newsErrorLiveData.observe(this, Observer {
                Log.d("ERROR", it.toString())
            })
        }
        viewModel.fetchTopHeadlineNews()
    }

    private fun initItemList() {
        viewNewsList.apply {
            adapter = NewsListAdapter { url -> newsActionFlowController?.displayNews(url) }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
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