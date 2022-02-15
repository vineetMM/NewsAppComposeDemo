package com.example.data.mappers

import com.example.domain.mappers.DomainModel

interface EntityMapper<M : DomainModel, ME : DataModel> {
  fun mapToDomain(entity: ME): M

  fun mapToData(model: M): ME
}

open class DataModel