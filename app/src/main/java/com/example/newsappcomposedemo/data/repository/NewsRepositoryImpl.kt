package com.example.newsappcomposedemo.data.repository

import com.example.newsappcomposedemo.data.remote.NewsDataSource
import com.example.newsappcomposedemo.model.ApiResponse
import com.example.newsappcomposedemo.util.SafeResult
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
  private val newsDataSource: NewsDataSource
) : NewsRepository {

  override suspend fun getTopArticles(): SafeResult<Response<ApiResponse>> {
    return when (val result = newsDataSource.fetchTopArticles("in")) {
      is SafeResult.Success -> SafeResult.Success(
        result.data
      )
      is SafeResult.Failure -> SafeResult.Failure(result.exception)
      SafeResult.NetworkError -> SafeResult.NetworkError
      else -> {
        throw RuntimeException()
      }
    }
  }
}

