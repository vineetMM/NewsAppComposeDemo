package com.example.newsappcomposedemo.data.repository

import com.example.newsappcomposedemo.model.ApiResponse
import com.example.newsappcomposedemo.util.SafeResult
import retrofit2.Response

interface NewsRepository {

  suspend fun getTopArticles(): SafeResult<Response<ApiResponse>>
}