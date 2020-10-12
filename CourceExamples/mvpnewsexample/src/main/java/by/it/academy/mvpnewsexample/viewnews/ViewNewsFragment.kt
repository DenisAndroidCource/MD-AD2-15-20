package by.it.academy.mvpnewsexample.viewnews

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import by.it.academy.mvpnewsexample.R
import kotlinx.android.synthetic.main.fragment_view_news.*

class ViewNewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_view_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        loadWebPageWithUrl(arguments?.getString(URL_BUNDLE_KEY) ?: "")
    }

    private fun loadWebPageWithUrl(url: String) {
        viewWebPage.loadUrl(url)
    }

    private fun initWebView() {
        with(viewWebPage) {
            settings.apply {
                loadsImagesAutomatically = true
                javaScriptEnabled = true
            }

            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    viewProgressBar.visibility = GONE
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    viewProgressBar.visibility = VISIBLE
                }

                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }
            }
        }
    }

    companion object {
        const val TAG = "ViewNewsFragment"
        private const val URL_BUNDLE_KEY = "URL_BUNDLE_KEY"

        fun newInstance(url: String) : ViewNewsFragment {
            val bundle = Bundle().apply { putString(URL_BUNDLE_KEY, url) }
            return ViewNewsFragment().apply { arguments = bundle }
        }
    }
}