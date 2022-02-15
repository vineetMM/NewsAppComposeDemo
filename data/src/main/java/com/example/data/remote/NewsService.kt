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
}