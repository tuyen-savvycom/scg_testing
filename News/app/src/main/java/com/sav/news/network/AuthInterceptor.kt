package com.sav.news.network

import com.sav.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getRequestWithOrWithoutAuthorizationHeader(chain)
        return chain.proceed(request)
    }

    private fun getRequestWithOrWithoutAuthorizationHeader(chain: Interceptor.Chain): Request {
        return chain.request().newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .url(
                chain.request().url.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build()
            )
            .build()
    }
}
