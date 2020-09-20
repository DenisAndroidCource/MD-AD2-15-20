package by.it.academy.newtworkexample.repo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

private const val API_KEY = "fe27628816ba4ca5b23fe932cf36e26e"

class NewRepositoryImpl(
    private val okHttpClient: OkHttpClient,
    private val newsDataModelMapper: (String) -> List<NewsDataModel>
) : NewsRepository {

    override fun getNewsTopHeadlines(country: String): Single<List<NewsDataModel>> {
        val url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=fe27628816ba4ca5b23fe932cf36e26e"
        val request = Request.Builder().url(url).build()
        return Single.create<String> { emitter ->
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) emitter.onError(Throwable("Server error code: ${response.code}"))
                if (response.body == null) emitter.onError(NullPointerException("Body is null"))
                emitter.onSuccess((response.body as ResponseBody).string())
            }
        }.subscribeOn(Schedulers.io())
            .map { jsonData -> newsDataModelMapper(jsonData) }
    }
}