package com.example.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

  @Query("SELECT * FROM articles")
  fun getAllArticles(): Flow<List<Article>>

  @Query("SELECT * FROM articles")
  fun getPagingArticles(): PagingSource<Int, Article>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllArticles(articles: List<Article>)

  @Query("DELETE FROM articles")
  suspend fun deleteAllArticles()
}