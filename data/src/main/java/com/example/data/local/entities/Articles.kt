package com.example.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.Source

@Entity(tableName = "articles")
data class Articles(

  @Embedded(prefix = "source_") val source: Source?,
  val author: String?,
  val title: String?,
  val description: String?,
  @PrimaryKey
  val url: String,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?,
  var isSaved: Boolean = true
)