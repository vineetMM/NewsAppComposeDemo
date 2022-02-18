package com.example.data.model

import com.example.data.local.entities.Article
import com.example.data.mappers.DataModel

data class Articles(
  val source: Source?,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?,
) : DataModel()

fun Articles.toDBArticle(): Article {
  return Article(
    this.source?.toDBSource(), this.author, this.title, this.description, this.url, this.urlToImage,
    this.publishedAt,
    this.content
  )
}