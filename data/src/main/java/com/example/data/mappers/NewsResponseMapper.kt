package com.example.data.mappers

import com.example.data.model.Articles
import com.example.data.model.NewsApiResponse
import com.example.data.model.Source
import com.example.data.model.toDOMSource
import com.example.domain.model.DOMArticle
import com.example.domain.model.DOMArticleList
import javax.inject.Inject

class NewsResponseMapper @Inject constructor() : EntityMapper<DOMArticleList, NewsApiResponse> {
  override fun mapToDomain(entity: NewsApiResponse): DOMArticleList {
    return DOMArticleList(
      entity.status,
      entity.totalResults,
      entity.articles.map {
        DOMArticle(
          it.source?.toDOMSource(), it.author, it.title, it.description, it.url, it.urlToImage,
          it.publishedAt,
          it.content
        )
      })
  }

  override fun mapToData(model: DOMArticleList): NewsApiResponse {
    return NewsApiResponse(model.status, model.totalResults, model.DOMArticles.map {
      Articles(
        it.source.let { DOMSource ->
          Source(DOMSource?.id, DOMSource?.name)
        }, it.author, it.title, it.description, it.url, it.urlToImage,
        it.publishedAt,
        it.content
      )
    })
  }
}