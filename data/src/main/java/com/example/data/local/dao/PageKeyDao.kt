package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.PageKey

@Dao
interface PageKeyDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllKeys(pageKeys: List<PageKey>)

  @Query("SELECT * FROM pageKey where url =:url")
  suspend fun getPageKey(url: String): PageKey

  @Query("DELETE FROM pageKey")
  suspend fun clearAllKeys()
}