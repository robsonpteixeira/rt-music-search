package com.robsonteixeira.musicsearch.features.musicdetails.data.repository.mapper

import com.robsonteixeira.musicsearch.features.musicdetails.data.api.model.DetailsResponse
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem

internal fun DetailsResponse.toDetailsItem() =
    DetailsItem(
        id = data.id,
        name = data.name,
        image = data.img.orEmpty(),
        src = data.src?.id.orEmpty(),
    )
