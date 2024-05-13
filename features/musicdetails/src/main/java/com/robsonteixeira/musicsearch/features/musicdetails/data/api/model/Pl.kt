package com.robsonteixeira.musicsearch.features.musicdetails.data.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Pl(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)
