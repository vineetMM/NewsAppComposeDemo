package com.example.newsappcomposedemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcomposedemo.data.repository.NewsRepository
import com.example.newsappcomposedemo.model.Articles
import com.example.newsappcomposedemo.util.SafeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val newsRepository: NewsRepository
) : ViewModel() {

  var newsList = MutableStateFlow<NewsViewState>(NewsViewState.Loading)
  private set

  init {
    getNews()
  }

  private fun getNews() = viewModelScope.launch(Dispatchers.IO) {
    when (val result = newsRepository.getTopArticles()) {
      is SafeResult.Success -> {
        result.data.body()?.let { safeResult ->
          newsList.value = NewsViewState.ShowNews(safeResult.articles)
        }
      }
      is SafeResult.Failure -> {
        newsList.value = NewsViewState.Error(result.message)
      }
      SafeResult.NetworkError -> {
        newsList.value = NewsViewState.Error("Network Error")
      }
    }
  }

  sealed class NewsViewState {
    object Loading : NewsViewState()
    class ShowNews(val news: List<Articles>) : NewsViewState()
    class Error(val message: String) : NewsViewState()
  }
}