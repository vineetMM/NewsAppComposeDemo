package com.example.newsappcomposedemo.ui.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.newsappcomposedemo.ui.common.ErrorItem
import com.example.newsappcomposedemo.ui.common.LoadingItem
import com.example.newsappcomposedemo.ui.common.LoadingView
import com.example.newsappcomposedemo.ui.common.NewsCard
import com.example.newsappcomposedemo.viewmodel.NewsViewModel

@Composable
fun NewsListPaging(newsViewModel: NewsViewModel) {

  val lazyNewsPaged = newsViewModel.newsPaged.collectAsLazyPagingItems()

  LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    contentPadding = PaddingValues(8.dp)
  ) {
    items(
      items = lazyNewsPaged,
      key = { article ->
        article.url
      }
    ) { article ->
      article?.let { NewsCard(articleUI = it) }
    }

    lazyNewsPaged.apply {
      when {
        loadState.refresh is LoadState.Loading -> {
          item { LoadingView(modifier = Modifier.fillMaxWidth()) }
        }
        loadState.append is LoadState.Loading -> {
          item { LoadingItem() }
        }
        loadState.refresh is LoadState.Error -> {
          val e = lazyNewsPaged.loadState.refresh as LoadState.Error
          item {
            ErrorItem(
              message = e.error.localizedMessage!!,
              modifier = Modifier.fillMaxWidth(),
              onClickRetry = { retry() }
            )
          }
        }
        loadState.append is LoadState.Error -> {
          val e = lazyNewsPaged.loadState.append as LoadState.Error
          item {
            ErrorItem(
              message = e.error.localizedMessage!!,
              onClickRetry = { retry() }
            )
          }
        }
      }
    }
  }
}
