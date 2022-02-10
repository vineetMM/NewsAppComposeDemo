package com.example.newsappcomposedemo.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Categories() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxHeight()
  ) {
    Text(text = "business")
    Text(text = "entertainment")
    Text(text = "general")
    Text(text = "health")
    Text(text = "science")
    Text(text = "sports")
    Text(text = "technology")
  }
}