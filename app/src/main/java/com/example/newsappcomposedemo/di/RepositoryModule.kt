package com.example.newsappcomposedemo.di

import com.example.newsappcomposedemo.data.repository.NewsRepository
import com.example.newsappcomposedemo.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun bindNewsRepository(
    newsRepositoryImpl: NewsRepositoryImpl
  ): NewsRepository
}