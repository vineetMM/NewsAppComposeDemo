package com.example.newsappcomposedemo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsappcomposedemo.viewmodel.NewsViewModel
import com.example.newsappcomposedemo.viewmodel.NewsViewModel.NewsViewState

@Composable
fun NewsList(newsViewModel: NewsViewModel) {
  val articlesList by newsViewModel.newsList.collectAsState()

  when (articlesList) {
    is NewsViewState.ShowNews -> {
      Scaffold {
        LazyColumn(
          modifier = Modifier.fillMaxWidth(),
          contentPadding = PaddingValues(8.dp)
        ) {
          item {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
              horizontalArrangement = Arrangement.Center,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                "Top Headlines",
                style = MaterialTheme.typography.h6
              )
            }
          }
          items((articlesList as NewsViewState.ShowNews).news) { article ->
            NewsCard(article)
          }
        }
      }
    }
    is NewsViewState.Error -> {
      Text(text = (articlesList as NewsViewState.Error).message)
    }

    is NewsViewState.Loading -> {
      LoadingComponent()
    }
  }
}