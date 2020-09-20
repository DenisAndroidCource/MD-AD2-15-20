package by.it.academy.newtworkexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.it.academy.newtworkexample.newslist.NewsListFragment
import by.it.academy.newtworkexample.viewnews.ViewNewsFragment

class MainActivity : AppCompatActivity(), NewsActionFlowController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNewsListFragment()
    }

    private fun showNewsListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, NewsListFragment.newInstance(), NewsListFragment.TAG)
            .commit()
    }

    private fun showViewNewsFragment(newsUrl: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ViewNewsFragment.newInstance(newsUrl), ViewNewsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun displayNews(url: String) {
        showViewNewsFragment(url)
    }
}
