package com.robsonteixeira.musicsearch.features.musicdetails.data.repository

import com.robsonteixeira.musicsearch.core.network.extensions.handleResponseAsResult
import com.robsonteixeira.musicsearch.features.musicdetails.data.api.DetailsApi
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.mapper.toDetailsItem
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val FORMAT = "json"

internal class DetailsRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApi,
): DetailsRepository {
    override suspend fun getDetails(id: String): Result<DetailsItem> =
        withContext(Dispatchers.IO) {
            detailsApi.getDetails(id, FORMAT).handleResponseAsResult {
                it.toDetailsItem()
            }
        }
}
