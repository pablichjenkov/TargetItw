package com.target.targetcasestudy.data

import okhttp3.Interceptor
import okhttp3.Response

class HttpErrorCodeInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val code = response.code
        return response;
    }

}