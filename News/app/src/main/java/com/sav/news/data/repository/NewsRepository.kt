package com.sav.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sav.news.data.remote.NewsApi
import com.sav.news.data.source.NewsPagingSource
import com.sav.news.models.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
) {

    fun getNewsList(
        coroutineScope: CoroutineScope,
        country: String,
        searchKeyword: String
    ): Flow<PagingData<News>> {
        val pagingSource = NewsPagingSource(newsApi, country, searchKeyword)

        return Pager(
            config = PagingConfig(pageSize = 5, initialLoadSize = 5),
            pagingSourceFactory = { pagingSource }
        ).flow.cachedIn(coroutineScope)
    }

}