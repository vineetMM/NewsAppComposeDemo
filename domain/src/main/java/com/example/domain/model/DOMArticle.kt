package com.example.domain.model

import com.example.domain.mappers.DomainModel

data class DOMArticle(
  val source: DOMSource?,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?,
) : DomainModel()

