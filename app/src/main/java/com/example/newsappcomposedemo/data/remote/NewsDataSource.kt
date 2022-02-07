package com.example.newsappcomposedemo.data.remote

import com.example.newsappcomposedemo.model.ApiResponse
import com.example.newsappcomposedemo.util.SafeResult
import com.example.newsappcomposedemo.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class NewsDataSource @Inject constructor(private val newsService: NewsService) {

  suspend fun fetchTopArticles(country: String): SafeResult<Response<ApiResponse>> {
    return safeApiCall(Dispatchers.IO) {
      newsService.getTopArticles(country)
    }
  }
}
