package com.example.newsappcomposedemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.common.SafeResult
import com.example.domain.usecases.GetTopNewsPagingUseCase
import com.example.domain.usecases.GetTopNewsUseCase
import com.example.newsappcomposedemo.model.ArticleUI
import com.example.newsappcomposedemo.model.UIArticleMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val useCaseGetTopNews: GetTopNewsUseCase,
  private val uiArticleMapper: UIArticleMapper,
  private val useCaseGetTopNewsPaging: GetTopNewsPagingUseCase
) : ViewModel() {

  var newsList = MutableStateFlow<NewsViewState>(NewsViewState.Loading)
    private set

  val newsPaged: Flow<PagingData<ArticleUI>> = useCaseGetTopNewsPaging().map { pagingData ->
    pagingData.map {
      uiArticleMapper.mapToPresentation(it)
    }
  }

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
    class ShowNews(val news: List<ArticleUI>) : NewsViewState()
    class Error(val message: String) : NewsViewState()
  }
}