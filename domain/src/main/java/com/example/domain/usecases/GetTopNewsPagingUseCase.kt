package com.example.domain.usecases

import androidx.paging.PagingData
import com.example.domain.model.DOMArticle
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopNewsPagingUseCase @Inject constructor(
  private val newsRepository: NewsRepository
) {
   operator fun invoke(): Flow<PagingData<DOMArticle>> {
     return newsRepository.getTopArticlesPaging()
   }
}