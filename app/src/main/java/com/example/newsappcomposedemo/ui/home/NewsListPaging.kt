package com.example.newsappcomposedemo.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.newsappcomposedemo.viewmodel.NewsViewModel
import com.example.newsappcomposedemo.viewmodel.NewsViewModel.NewsViewStatePaging

@Composable
fun NewsListPaging(newsViewModel: NewsViewModel) {
  val getAllNews by newsViewModel.newsListPaging.collectAsState()

  when (getAllNews) {
    is NewsViewStatePaging.ShowNews -> {
      val items = (getAllNews as NewsViewStatePaging.ShowNews).news.collectAsLazyPagingItems()
      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
      ) {
        items(
          items = items,
          key = { article ->
            article.url
          }
        ) { article ->
          article?.let { NewsCard(articles = it) }
        }
      }
    }

    is NewsViewStatePaging.Error -> {
      Text(text = "Error")
    }

    is NewsViewStatePaging.Loading -> {
      LoadingComponent()
    }
  }
}