package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.common.Constants.PAGE_SIZE
import com.example.data.common.safeApiCall
import com.example.data.local.AppDatabase
import com.example.data.local.entities.toDOMArticle
import com.example.data.mappers.EntityMapper
import com.example.data.model.NewsApiResponse
import com.example.data.paging.NewsRemoteMediator
import com.example.data.remote.NewsService
import com.example.domain.common.SafeResult
import com.example.domain.model.DOMArticle
import com.example.domain.model.DOMArticleList
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
  private val newsApi: NewsService,
  private val DOMNewsResponseMapper: EntityMapper<DOMArticleList, NewsApiResponse>,
  private val appDatabase: AppDatabase
) : NewsRepository {
  override suspend fun getTopArticles(): SafeResult<DOMArticleList> {
    return when (val result = safeApiCall(Dispatchers.IO) { newsApi.getTopArticles("in") }) {
      is SafeResult.Success -> SafeResult.Success(
        DOMNewsResponseMapper.mapToDomain(result.data)
      )
      is SafeResult.Failure -> SafeResult.Failure(result.exception)
      is SafeResult.NetworkError -> SafeResult.NetworkError
      else -> {
        throw RuntimeException()
      }
    }
  }

  @OptIn(ExperimentalPagingApi::class)
  override fun getTopArticlesPaging(): Flow<PagingData<DOMArticle>> {
    val pagingSourceFactory = { appDatabase.getArticlesDao().getPagingArticles() }
    return Pager(
      config = PagingConfig(pageSize = PAGE_SIZE),
      remoteMediator = NewsRemoteMediator(newsApi, appDatabase),
      pagingSourceFactory = pagingSourceFactory
    ).flow.map { pagingData ->
      pagingData.map { article -> article.toDOMArticle() }

    }
  }
}