package com.example.data.di

import com.example.data.mappers.EntityMapper
import com.example.data.mappers.NewsResponseMapper
import com.example.data.model.NewsApiResponse
import com.example.domain.model.DOMArticleList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataMappersModule {

  @Binds
  @Singleton
  abstract fun bindNewsArticleMapper(newsResponseMapper: NewsResponseMapper): EntityMapper<DOMArticleList, NewsApiResponse>
}