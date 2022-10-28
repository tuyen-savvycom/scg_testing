package com.sav.news.data.remote

import com.sav.news.data.models.GetNewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("q") searchKeyword: String
    ): Response<GetNewsListResponse>

}