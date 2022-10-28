package com.sav.news.network

import android.app.Application
import com.google.gson.GsonBuilder
import com.sav.news.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = createGsonConverter()

    private fun createGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache =
        Cache(application.cacheDir, 10L * 1024L * 1024L)

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        cache: Cache,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(logging)
            .cache(cache)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gson)
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}