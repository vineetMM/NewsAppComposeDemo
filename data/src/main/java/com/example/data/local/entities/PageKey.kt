package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pageKey")
data class PageKey(
  @PrimaryKey(autoGenerate = false)
  val url: String,
  val prevPage: Int?,
  val nextPage: Int?
)
