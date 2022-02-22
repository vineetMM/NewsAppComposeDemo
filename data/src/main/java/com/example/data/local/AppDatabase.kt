package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.ArticlesDao
import com.example.data.local.dao.PageKeyDao
import com.example.data.local.entities.Article
import com.example.data.local.entities.PageKey

@Database(entities = [Article::class, PageKey::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun getArticlesDao(): ArticlesDao

  abstract fun getPageKeyDao(): PageKeyDao

  companion object {
    @Volatile
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase =
      instance ?: synchronized(this) {
        instance ?: buildDatabase(context).also {
          instance = it
        }
      }

    private fun buildDatabase(appContext: Context) =
      Room.databaseBuilder(appContext, AppDatabase::class.java, "news.db")
        .fallbackToDestructiveMigration()
        .build()
  }
}
