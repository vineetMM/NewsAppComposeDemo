package com.example.data.model

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
