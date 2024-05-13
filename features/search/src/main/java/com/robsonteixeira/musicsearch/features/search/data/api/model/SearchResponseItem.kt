package com.robsonteixeira.musicsearch.features.search.data.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class SearchResponseItem(

	@Json(name="eId")
	val eId: String? = null,

	@Json(name="uId")
	val uId: String? = null,

	@Json(name="score")
	val score: Int? = null,

	@Json(name="img")
	val img: String? = null,

	@Json(name="uNm")
	val uNm: String? = null,

	@Json(name="name")
	val name: String,

	@Json(name="id")
	val id: String,

	@Json(name="url")
	val url: String? = null
)
