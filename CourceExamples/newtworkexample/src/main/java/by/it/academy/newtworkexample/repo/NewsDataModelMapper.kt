package by.it.academy.newtworkexample.repo

import org.json.JSONObject

class NewsDataModelMapper : (String) -> List<NewsDataModel> {

    override fun invoke(jsonData: String): List<NewsDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArticlesArray = jsonObject.getJSONArray("articles")
        if (jsonArticlesArray.length() != 0) {
            val itemList = mutableListOf<NewsDataModel>()
            for (index in 0 until jsonArticlesArray.length()) {
                val dataModel = with(jsonArticlesArray.getJSONObject(index)) {
                    NewsDataModel(
                        title = getString("title"),
                        description = getString("description"),
                        url = getString("url"),
                        urlToImage = getString("urlToImage"),
                    )
                }
                itemList.add(dataModel)
            }
            return itemList
        }
        return emptyList()
    }
}