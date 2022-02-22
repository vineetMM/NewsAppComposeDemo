package com.example.newsappcomposedemo.model

import com.example.domain.mappers.UIModel
import com.example.domain.mappers.UiModelMapper
import com.example.domain.model.DOMArticle
import com.example.domain.model.DOMSource

data class ArticleUI(
  val sourceUI: SourceUI?,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?,
) : UIModel()

class UIArticleMapper : UiModelMapper<DOMArticle, ArticleUI> {
  override fun mapToPresentation(model: DOMArticle): ArticleUI {
    return ArticleUI(
      model.source.let { DOMSource ->
        SourceUI(DOMSource?.id, DOMSource?.name)
      }, model.author, model.title, model.description, model.url, model.urlToImage,
      model.publishedAt,
      model.content
    )
  }

  override fun mapToDomain(modelItem: ArticleUI): DOMArticle {
    return DOMArticle(
      modelItem.sourceUI.let { source ->
        DOMSource(source?.id, source?.name)
      }, modelItem.author, modelItem.title, modelItem.description, modelItem.url,
      modelItem.urlToImage,
      modelItem.publishedAt,
      modelItem.content
    )
  }
}
