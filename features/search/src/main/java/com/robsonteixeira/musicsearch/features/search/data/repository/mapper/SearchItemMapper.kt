package com.robsonteixeira.musicsearch.features.search.data.repository.mapper

import com.robsonteixeira.musicsearch.features.search.data.api.model.SearchResponseItem
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem

internal fun SearchResponseItem.toSearchItem() =
    SearchItem(
        id = id,
        name = name,
    )
