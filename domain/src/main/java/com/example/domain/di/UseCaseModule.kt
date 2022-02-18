package com.example.domain.di

import com.example.domain.repository.NewsRepository
import com.example.domain.usecases.GetTopNewsPagingUseCase
import com.example.domain.usecases.GetTopNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

  @Provides
  @ViewModelScoped
  fun provideUseCaseGetTopNews(newsRepository: NewsRepository) =
    GetTopNewsUseCase(newsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseGetTopNewsPaging(newsRepository: NewsRepository) =
    GetTopNewsPagingUseCase(newsRepository)
}