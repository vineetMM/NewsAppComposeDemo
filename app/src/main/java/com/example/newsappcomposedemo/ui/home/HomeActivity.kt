package com.example.newsappcomposedemo.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import com.example.newsappcomposedemo.ui.tabs.TabScreen
import com.example.newsappcomposedemo.ui.theme.NewsAppComposeDemoTheme
import com.example.newsappcomposedemo.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  val viewModel: NewsViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      NewsAppComposeDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Scaffold {
            TabScreen(viewModel = viewModel)
          }
        }
      }
    }
  }
}

