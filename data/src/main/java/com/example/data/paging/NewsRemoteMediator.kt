package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.common.Constants.PAGE_SIZE
import com.example.data.local.AppDatabase
import com.example.data.local.entities.Article
import com.example.data.local.entities.PageKey
import com.example.data.model.toDBArticle
import com.example.data.remote.NewsService
import javax.inject.Inject

@ExperimentalPagingApi
class NewsRemoteMediator @Inject constructor(
  private val newsApi: NewsService,
  private val newsDatabase: AppDatabase
) : RemoteMediator<Int, Article>() {
  private val articlesDao = newsDatabase.getArticlesDao()
  private val pageKeyDao = newsDatabase.getPageKeyDao()
  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, Article>
  ): MediatorResult {
    return try {
      val currentPage = when (loadType) {
        LoadType.REFRESH -> {
          val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
          remoteKeys?.nextPage?.minus(1) ?: 1
        }
        LoadType.PREPEND -> {
          val remoteKeys = getRemoteKeyForFirstItem(state)
          val prevPage = remoteKeys?.prevPage
            ?: return MediatorResult.Success(
              endOfPaginationReached = remoteKeys != null
            )
          prevPage
        }
        LoadType.APPEND -> {
          val remoteKeys = getRemoteKeyForLastItem(state)
          val nextPage = remoteKeys?.nextPage
            ?: return MediatorResult.Success(
              endOfPaginationReached = remoteKeys != null
            )
          nextPage
        }
      }
      val response = newsApi.getTopArticlesPaging(
        page = currentPage, pageSize = PAGE_SIZE, country = "in"
      )

      val endOfPaginationReached = response.articles.isEmpty()

      val prevPage = if (currentPage == 1) null else currentPage - 1
      val nextPage = if (endOfPaginationReached) null else currentPage + 1

      newsDatabase.withTransaction {
        if (loadType == REFRESH) {
          articlesDao.deleteAllArticles()
          pageKeyDao.clearAllKeys()
        }
        val keys = response.articles.map { article ->
          PageKey(
            url = article.url,
            prevPage = prevPage,
            nextPage = nextPage
          )
        }
        pageKeyDao.insertAllKeys(pageKeys = keys)
        articlesDao.insertAllArticles(articles = response.articles.map { it.toDBArticle() })
      }
      MediatorResult.Success(
        endOfPaginationReached = endOfPaginationReached
      )
    } catch (e: Exception) {
      MediatorResult.Error(e)
    }
  }

  private suspend fun getRemoteKeyClosestToCurrentPosition(
    state: PagingState<Int, Article>
  ): PageKey? {
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.url?.let { url ->
        pageKeyDao.getPageKey(url = url)
      }
    }
  }

  private suspend fun getRemoteKeyForFirstItem(
    state: PagingState<Int, Article>
  ): PageKey? {
    return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
      ?.let { article ->
        pageKeyDao.getPageKey(url = article.url)
      }
  }

  private suspend fun getRemoteKeyForLastItem(
    state: PagingState<Int, Article>
  ): PageKey? {
    return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
      ?.let { article ->
        pageKeyDao.getPageKey(url = article.url)
      }
  }
}