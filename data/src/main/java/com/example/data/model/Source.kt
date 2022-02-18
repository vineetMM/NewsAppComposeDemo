package com.example.data.model

import com.example.domain.model.DOMSource

data class Source(
  val id: String?,
  val name: String?

)

fun Source.toDOMSource(): DOMSource {
  return DOMSource(
    this.id,
    this.name
  )
}

fun Source.toDBSource(): Source {
  return Source(
    this.id,
    this.name
  )
}
