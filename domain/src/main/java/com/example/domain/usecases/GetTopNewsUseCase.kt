package com.example.domain.usecases

import com.example.domain.common.SafeResult
import com.example.domain.model.DOMArticle
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(
  private val newsRepository: NewsRepository
) {
  suspend operator fun invoke(): SafeResult<List<DOMArticle>> {
    return when (val result = newsRepository.getTopArticles()) {
      is SafeResult.Success -> SafeResult.Success(result.data.DOMArticles)
      is SafeResult.NetworkError -> result
      is SafeResult.Failure -> result
    }
  }
}