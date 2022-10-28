package com.sav.news.data.models

import com.google.gson.annotations.SerializedName
import com.sav.news.models.News
import com.sav.news.utils.extensions.formatDateTime

data class GetNewsListResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val articles: List<NewsResponse>?,
) {
    fun toNewsList(): List<News> {
        return articles?.map { newsResponse ->
            News(
                title = newsResponse.title,
                description = newsResponse.description,
                imageUrl = newsResponse.urlToImage,
                content = newsResponse.content,
                publishedAt = "Updated: ${newsResponse.publishedAt?.formatDateTime()}",
            )
        }?.toList() ?: emptyList()
    }
}

data class NewsResponse(
    @SerializedName("source")
    val source: Source?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?,
)

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
)