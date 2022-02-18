package com.example.data.di

import android.content.Context
import com.example.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext appContext: Context) =
    AppDatabase.getDatabase(appContext)

  @Singleton
  @Provides
  fun provideArticlesDao(database: AppDatabase) = database.getArticlesDao()

  @Singleton
  @Provides
  fun providePageKeyDao(database: AppDatabase) = database.getPageKeyDao()
}