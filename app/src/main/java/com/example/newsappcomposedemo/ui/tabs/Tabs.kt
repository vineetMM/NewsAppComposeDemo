package com.example.newsappcomposedemo.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsappcomposedemo.ui.categories.Categories
import com.example.newsappcomposedemo.ui.news.NewsList
import com.example.newsappcomposedemo.ui.news.NewsListPaging
import com.example.newsappcomposedemo.viewmodel.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(viewModel: NewsViewModel) {
  val pagerState = rememberPagerState(pageCount = 3, initialPage = 1)
  Column(
    modifier = Modifier.background(Color.White)
  ) {
    Tabs(pagerState = pagerState)
    TabsContent(pagerState = pagerState, viewModel)
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
  val list = listOf("Interests", "Top News", "My Feed")
  val scope = rememberCoroutineScope()

  TabRow(
    selectedTabIndex = pagerState.currentPage,
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = Color.White,
    divider = {
      TabRowDefaults.Divider(
        thickness = 2.dp,
        color = MaterialTheme.colors.primary
      )
    },
    indicator = { tabPositions ->
      TabRowDefaults.Indicator(
        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
        height = 2.dp,
        color = Color.White
      )
    }
  ) {
    list.forEachIndexed { index, _ ->
      Tab(
        text = {
          Text(
            list[index],
            color = if (pagerState.currentPage == index) Color.White else Color.LightGray
          )
        },
        selected = pagerState.currentPage == index,
        onClick = {
          scope.launch {
            pagerState.animateScrollToPage(index)
          }
        }
      )
    }
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
  pagerState: PagerState,
  viewModel: NewsViewModel
) {
  HorizontalPager(state = pagerState) { page ->
    when (page) {
      0 -> Categories()
      1 -> NewsListPaging(newsViewModel = viewModel)
      2 -> NewsList(newsViewModel = viewModel)
    }
  }
}