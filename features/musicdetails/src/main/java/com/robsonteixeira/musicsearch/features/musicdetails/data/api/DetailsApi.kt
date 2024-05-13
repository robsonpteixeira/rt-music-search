package com.robsonteixeira.musicsearch.features.musicdetails.data.api

import com.robsonteixeira.musicsearch.features.musicdetails.data.api.model.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface DetailsApi {
    @GET("c/{id}/")
    suspend fun getDetails(@Path("id") id: String, @Query("format") format: String): Response<DetailsResponse>
}
