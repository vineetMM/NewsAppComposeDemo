package com.example.newsappcomposedemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.SafeResult
import com.example.domain.usecases.GetTopNewsUseCase
import com.example.newsappcomposedemo.model.Articles
import com.example.newsappcomposedemo.model.UIArticleMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val useCaseGetTopNews: GetTopNewsUseCase,
  private val uiArticleMapper: UIArticleMapper
) : ViewModel() {

  var newsList = MutableStateFlow<NewsViewState>(NewsViewState.Loading)
    private set

  init {
    getNews()
  }

  private fun getNews() = viewModelScope.launch(Dispatchers.IO) {
    when (val result = useCaseGetTopNews()) {
      is SafeResult.Success -> {
        result.data.let { safeResult ->
          newsList.value =
            NewsViewState.ShowNews(safeResult.map { uiArticleMapper.mapToPresentation(it) })
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