package com.sav.news.data.usecase

import androidx.paging.Pager
import com.sav.news.data.repository.NewsRepository
import com.sav.news.models.News
import javax.inject.Inject

class NewsUserCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(
        country: String,
        searchKeyword: String,
    ): Pager<Int, News> {
        return newsRepository.getNewsList(country, searchKeyword)
    }

}