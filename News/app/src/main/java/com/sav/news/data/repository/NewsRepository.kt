package com.sav.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sav.news.data.remote.NewsApi
import com.sav.news.data.source.NewsPagingSource
import com.sav.news.models.News
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
) {

    fun getNewsList(
        country: String,
        searchKeyword: String
    ): Pager<Int, News> {
        val pagingSource = NewsPagingSource(newsApi, country, searchKeyword)

        return Pager(
            config = PagingConfig(pageSize = 5, initialLoadSize = 5),
            pagingSourceFactory = { pagingSource }
        )
    }

}