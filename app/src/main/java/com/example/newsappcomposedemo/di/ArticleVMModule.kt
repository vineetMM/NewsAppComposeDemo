package com.example.newsappcomposedemo.di

import com.example.newsappcomposedemo.model.UIArticleMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ArticleVMModule {

  @Provides
  fun provideUiModelMapper(): UIArticleMapper {
    return UIArticleMapper()
  }
}