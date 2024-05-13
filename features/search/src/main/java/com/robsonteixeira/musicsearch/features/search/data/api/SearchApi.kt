package com.robsonteixeira.musicsearch.features.search.data.api

import com.robsonteixeira.musicsearch.features.search.data.api.model.SearchResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchApi {
    @GET("search")
    suspend fun getSearch(
        @Query("context") context: String, @Query("q") query: String
    ):Response<List<SearchResponseItem>>
}
