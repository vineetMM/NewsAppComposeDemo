package com.example.domain.repository

import com.example.domain.common.SafeResult
import com.example.domain.model.DOMArticleList

interface NewsRepository {

  suspend fun getTopArticles(): SafeResult<DOMArticleList>
}