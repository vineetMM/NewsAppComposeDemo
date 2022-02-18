package com.example.data.remote

import com.example.data.common.Constants
import com.example.data.model.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

  @GET(Constants.TOP_HEADLINE)
  suspend fun getTopArticles(
    @Query("country") country: String,
  ): NewsApiResponse

  @GET(Constants.TOP_HEADLINE)
  suspend fun getTopArticlesPaging(
    @Query("page") page: Int,
    @Query("country") country: String,
    @Query("pageSize") pageSize: Int
  ): NewsApiResponse
}