package com.example.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.domain.common.SafeResult
import com.example.domain.model.DOMArticle
import com.example.domain.model.DOMArticleList
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

  suspend fun getTopArticles(): SafeResult<DOMArticleList>

  @ExperimentalPagingApi
  suspend fun getTopArticlesPaging(): Flow<PagingData<DOMArticle>>
}