package com.sav.news.data.module

import com.sav.news.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    fun provideRepository(retrofit: Retrofit): NewsApi =
        retrofit.create()
}