package com.example.data.common

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIKeyInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()

    val key = "7bc3421e6c7d4acb86d8ced2e8f4bf67"

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
