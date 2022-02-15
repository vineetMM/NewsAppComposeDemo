package com.example.data.sources

import com.example.data.model.NewsApiResponse
import com.example.domain.common.SafeResult

interface INewsDataSource {

  suspend fun fetchTopArticles(country: String): SafeResult<NewsApiResponse>
}