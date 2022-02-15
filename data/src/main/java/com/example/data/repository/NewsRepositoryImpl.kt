package com.example.data.repository

import com.example.data.mappers.EntityMapper
import com.example.data.model.NewsApiResponse
import com.example.data.sources.INewsDataSource
import com.example.domain.common.SafeResult
import com.example.domain.model.DOMArticleList
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
  private val remoteDataSource: INewsDataSource,
  private val DOMNewsResponseMapper: EntityMapper<DOMArticleList, NewsApiResponse>
) : NewsRepository {
  override suspend fun getTopArticles(): SafeResult<DOMArticleList> {
    return when (val result = remoteDataSource.fetchTopArticles("in")) {
      is SafeResult.Success -> SafeResult.Success(
        DOMNewsResponseMapper.mapToDomain(result.data)
      )
      is SafeResult.Failure -> SafeResult.Failure(result.exception)
      SafeResult.NetworkError -> SafeResult.NetworkError
      else -> {
        throw RuntimeException()
      }
    }
  }
}