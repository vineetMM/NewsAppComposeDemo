package com.example.newsappcomposedemo.data.remote

import com.example.newsappcomposedemo.model.ApiResponse
import com.example.newsappcomposedemo.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

  @GET(Constants.TOP_HEADLINE)
  suspend fun getTopArticles(
    @Query("country") country: String,
  ): Response<ApiResponse>
}