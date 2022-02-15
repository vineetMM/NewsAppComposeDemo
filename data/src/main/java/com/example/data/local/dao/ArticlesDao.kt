package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.local.entities.Articles
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

  @Query("SELECT * FROM articles")
  fun getAllArticles(): Flow<List<Articles>>
}