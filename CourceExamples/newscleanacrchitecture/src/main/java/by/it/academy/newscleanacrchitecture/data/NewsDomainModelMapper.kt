package by.it.academy.newscleanacrchitecture.data

import by.it.academy.newscleanacrchitecture.domain.NewsDomainModel

class NewsDomainModelMapper : (List<NewsDataModel>) -> List<NewsDomainModel> {
    override fun invoke(newsDataModelList: List<NewsDataModel>) =
        newsDataModelList.map { item ->
            NewsDomainModel(
                title = item.title,
                description = item.description,
                url = item.url,
                urlToImage = item.urlToImage
            )
        }
}