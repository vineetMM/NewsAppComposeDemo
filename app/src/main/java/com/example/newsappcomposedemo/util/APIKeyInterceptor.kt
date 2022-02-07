package com.example.newsappcomposedemo.util

import com.example.newsappcomposedemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIKeyInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()

    val key = BuildConfig.API_KEY

    val request = original.newBuilder()
      .addHeader(
        "Authorization",
        "Bearer $key"
      )
      .method(original.method, original.body)
      .build()
    return chain.proceed(request)
  }
}
