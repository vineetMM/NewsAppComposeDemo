package com.example.data.model

import com.example.domain.model.DOMSource
import com.google.gson.annotations.SerializedName

data class Source(
  @SerializedName("id") val id: String?,
  @SerializedName("name") val name: String?

)

fun Source.toDOMSource(): DOMSource {
  return DOMSource(
    this.id,
    this.name
  )
}
