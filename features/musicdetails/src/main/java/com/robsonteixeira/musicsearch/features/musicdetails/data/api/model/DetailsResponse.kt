package com.robsonteixeira.musicsearch.features.musicdetails.data.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class DetailsResponse(

	@Json(name="data")
	val data: Data
)
