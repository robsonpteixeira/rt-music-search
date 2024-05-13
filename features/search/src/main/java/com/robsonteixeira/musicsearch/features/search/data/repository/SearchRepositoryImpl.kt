package com.robsonteixeira.musicsearch.features.search.data.repository

import com.robsonteixeira.musicsearch.core.network.extensions.handleResponseAsResult
import com.robsonteixeira.musicsearch.features.search.data.api.SearchApi
import com.robsonteixeira.musicsearch.features.search.data.repository.mapper.toSearchItem
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val QUERY_CONTEXT = "addTrack"

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
): SearchRepository {
    override suspend fun getSearch(query: String): Result<List<SearchItem>> =
        withContext(Dispatchers.IO) {
            searchApi.getSearch(QUERY_CONTEXT, query).handleResponseAsResult {
                it.map { item ->
                    item.toSearchItem()
                }
            }
        }
}
