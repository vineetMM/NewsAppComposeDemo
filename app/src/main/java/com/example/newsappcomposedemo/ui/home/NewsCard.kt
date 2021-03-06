package com.example.newsappcomposedemo.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.newsappcomposedemo.model.Articles
import com.example.newsappcomposedemo.ui.theme.NewsAppComposeDemoTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewsCard(articles: Articles) {
  var expandedState by remember {
    mutableStateOf(false)
  }

  val rotationState by animateFloatAsState(
    targetValue = if (expandedState) 180f else 0f
  )

  val context = LocalContext.current
  Card(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articles.url))
        startActivity(context, browserIntent, null)
      },
    shape = MaterialTheme.shapes.medium,
    backgroundColor = MaterialTheme.colors.background,
    elevation = 8.dp,

    ) {
    Column(
      verticalArrangement = Arrangement.Center,
    ) {
      NewsImage(articles = articles)

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = articles.title ?: "No data found", fontWeight = FontWeight.Bold,
          modifier = Modifier
            .padding(8.dp, 8.dp, 0.dp, 4.dp)
            .weight(6f)
        )
        IconButton(
          modifier = Modifier
            .alpha(ContentAlpha.medium)
            .rotate(rotationState)
            .weight(1f),
          onClick = {
            expandedState = !expandedState
          }) {
          Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop-Down")
        }
      }
      AnimatedVisibility(
        visible = expandedState,
        enter = expandVertically(
          animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
        ),
        exit = shrinkVertically(
          animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
        )
      ) {
        Text(
          text = articles.description ?: "No data found",
          modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
        )
      }
    }
  }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NewsImage(articles: Articles) {
  Image(
    painter = rememberImagePainter(articles.urlToImage),
    contentDescription = null,
    modifier = Modifier
      .fillMaxWidth()
      .height(200.dp),
    contentScale = ContentScale.Crop,
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
  val articles = Articles(
    null, "author",
    "ABC Suspends Whoopi Goldberg Over Holocaust Comments - The New York Times",
    "Ms. Goldberg???s comments, on Monday???s episode of ???The View,??? came amid growing ignorance about the Holocaust and rising antisemitism. She has apologized.",
    "https://www.nytimes.com/2022/02/01/us/whoopi-goldberg-holocaust.html",
    "https://static01.nyt.com/images/2022/02/01/multimedia/01whoopi-promo/01whoopi-promo-facebookJumbo.jpg",
    null, null
  )
  NewsAppComposeDemoTheme {
    NewsCard(articles)
  }
}