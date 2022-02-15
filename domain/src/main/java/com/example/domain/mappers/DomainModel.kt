package com.example.domain.mappers

open class DomainModel

open class UIModel

interface UiModelMapper<M : DomainModel, MI : UIModel> {
  fun mapToPresentation(model: M): MI

  fun mapToDomain(modelItem: MI): M
}