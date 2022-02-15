package com.example.data.di

import com.example.data.remote.NewsService
import com.example.data.sources.INewsDataSource
import com.example.data.sources.NewsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

  @Provides
  fun provideNewsDataSource(apiService: NewsService): INewsDataSource {
    return NewsDataSource(apiService)
  }
}