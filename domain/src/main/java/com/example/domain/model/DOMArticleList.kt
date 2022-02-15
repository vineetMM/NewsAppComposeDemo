package com.example.domain.model

import com.example.domain.mappers.DomainModel

data class DOMArticleList(
  val status: String,
  val totalResults: Int,
  val DOMArticles: List<DOMArticle>
) : DomainModel()
