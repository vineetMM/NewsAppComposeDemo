package com.example.data.sources

import com.example.data.common.safeApiCall
import com.example.data.model.NewsApiResponse
import com.example.data.remote.NewsService
import com.example.domain.common.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class NewsDataSource(
  private val newsService: NewsService,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : INewsDataSource {
  override suspend fun fetchTopArticles(country: String): SafeResult<NewsApiResponse> {
    return safeApiCall(dispatcher) {
      newsService.getTopArticles(country)
    }
  }

  override suspend fun fetchTopArticlesPaging(
    page: Int,
    country: String,
    perPage: Int
  ): SafeResult<NewsApiResponse> {
    return safeApiCall(dispatcher) {
      newsService.getTopArticlesPaging(
        page, country, perPage
      )
    }
  }
}