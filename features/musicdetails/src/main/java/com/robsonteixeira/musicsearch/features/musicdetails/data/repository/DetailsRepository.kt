package com.robsonteixeira.musicsearch.features.musicdetails.data.repository

import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem

internal interface DetailsRepository {
    suspend fun getDetails(id: String): Result<DetailsItem>
}
