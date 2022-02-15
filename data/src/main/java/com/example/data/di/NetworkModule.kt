package com.example.data.di

import com.example.data.common.APIKeyInterceptor
import com.example.data.common.Constants.BASE_URL
import com.example.data.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

  @Singleton
  @Provides
  fun provideHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiKeyInterceptor: APIKeyInterceptor
  ): OkHttpClient {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient
      .Builder()
      .readTimeout(15, TimeUnit.SECONDS)
      .connectTimeout(15, TimeUnit.SECONDS)
      .addInterceptor(logger)
      .addInterceptor(httpLoggingInterceptor)
      .addInterceptor(apiKeyInterceptor)
      .build()
  }

  @Singleton
  @Provides
  fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

  @Singleton
  @Provides
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(gsonConverterFactory)
      .build()
  }

  @Provides
  @Singleton
  fun provideUsersApi(retrofit: Retrofit): NewsService =
    retrofit.create(NewsService::class.java)
}