package com.example.newsappcomposedemo.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//TODO: this feature is pending
@Composable
fun Categories() {
  val category = listOf(
    "Business", "Entertainment", "General", "Health",
    "Science", "Sports", "Technology"
  )
  Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    category.forEach { items ->
      Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        val isChecked = remember { mutableStateOf(false) }
        Checkbox(
          checked = isChecked.value,
          modifier = Modifier.padding(8.dp),
          onCheckedChange = { isChecked.value = it },
          enabled = true,
        )
        Text(text = items, fontSize = 16.sp)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
  Categories()
}