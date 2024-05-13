package com.robsonteixeira.musicsearch.features.search.data.repository

import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem

internal interface SearchRepository {
    suspend fun getSearch(query: String): Result<List<SearchItem>>
}
