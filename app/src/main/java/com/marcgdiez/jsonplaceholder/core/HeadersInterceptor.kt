package com.marcgdiez.jsonplaceholder.core

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        request.addHeader(NetworkConfig.CONTENT_TYPE_KEY, NetworkConfig.CONTENT_TYPE_VALUE)

        return chain.proceed(request.build())
    }
}

