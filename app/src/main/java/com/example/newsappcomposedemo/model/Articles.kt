package com.example.newsappcomposedemo.model

import com.example.domain.mappers.UIModel
import com.example.domain.mappers.UiModelMapper
import com.example.domain.model.DOMArticle
import com.example.domain.model.DOMSource

data class Articles(
  val source: Source?,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?,
) : UIModel()

class UIArticleMapper : UiModelMapper<DOMArticle, Articles> {
  override fun mapToPresentation(model: DOMArticle): Articles {
    return Articles(
      model.source.let { DOMSource ->
        Source(DOMSource?.id, DOMSource?.name)
      }, model.author, model.title, model.description, model.url, model.urlToImage,
      model.publishedAt,
      model.content
    )
  }

  override fun mapToDomain(modelItem: Articles): DOMArticle {
    return DOMArticle(
      modelItem.source.let { source ->
        DOMSource(source?.id, source?.name)
      }, modelItem.author, modelItem.title, modelItem.description, modelItem.url,
      modelItem.urlToImage,
      modelItem.publishedAt,
      modelItem.content
    )
  }
}
