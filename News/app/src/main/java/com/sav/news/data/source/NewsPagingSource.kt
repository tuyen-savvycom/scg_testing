package com.sav.news.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sav.news.data.remote.NewsApi
import com.sav.news.models.News

class NewsPagingSource(
    private val api: NewsApi,
    private val country: String,
    private val searchKeyword: String
) : PagingSource<Int, News>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val nextPage = params.key ?: INITIAL_PAGE
            val pageSize = params.loadSize
            val response = api.getNews(
                country = country,
                page = nextPage,
                pageSize = pageSize,
                searchKeyword = searchKeyword
            )

            val data = response.body()?.toNewsList() ?: emptyList()
            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (data.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val INITIAL_PAGE = 1
    }

}