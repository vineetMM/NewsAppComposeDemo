package com.example.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.Source
import com.example.data.model.toDOMSource
import com.example.domain.model.DOMArticle

@Entity(tableName = "articles")
data class Article(

  @Embedded(prefix = "source_") val source: Source?,
  val author: String?,
  val title: String?,
  val description: String?,
  @PrimaryKey
  val url: String,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?,
)

fun Article.toDOMArticle(): DOMArticle {
  return DOMArticle(
    this.source?.toDOMSource(), this.author, this.title, this.description, this.url,
    this.urlToImage,
    this.publishedAt,
    this.content
  )
}