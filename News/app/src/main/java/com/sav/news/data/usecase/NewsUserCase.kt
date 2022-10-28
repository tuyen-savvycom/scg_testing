package com.sav.news.data.usecase

import androidx.paging.PagingData
import com.sav.news.data.repository.NewsRepository
import com.sav.news.models.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUserCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(
        coroutineScope: CoroutineScope,
        country: String,
        searchKeyword: String,
    ): Flow<PagingData<News>> {
        return newsRepository.getNewsList(coroutineScope, country, searchKeyword)
    }

}