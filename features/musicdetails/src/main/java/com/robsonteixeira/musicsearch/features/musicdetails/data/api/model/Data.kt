package com.robsonteixeira.musicsearch.features.musicdetails.data.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Data(

	@Json(name="eId")
	val eId: String? = null,

	@Json(name="img")
	val img: String? = null,

	@Json(name="comments")
	val comments: List<Any?>? = null,

	@Json(name="src")
	val src: Src? = null,

	@Json(name="ctx")
	val ctx: String? = null,

	@Json(name="uId")
	val uId: String? = null,

	@Json(name="uNm")
	val uNm: String? = null,

	@Json(name="name")
	val name: String,

	@Json(name="_id")
	val id: String,

	@Json(name="text")
	val text: String? = null,

	@Json(name="pl")
	val pl: Pl? = null,

	@Json(name="nbP")
	val nbP: Int? = null,

	@Json(name="reposts")
	val reposts: List<Any?>? = null
)
