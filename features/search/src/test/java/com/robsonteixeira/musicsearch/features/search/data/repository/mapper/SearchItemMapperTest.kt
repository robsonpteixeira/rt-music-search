package com.robsonteixeira.musicsearch.features.search.data.repository.mapper

import com.robsonteixeira.musicsearch.features.search.data.api.model.SearchResponseItem
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem
import org.junit.Assert
import org.junit.Test

internal class SearchItemMapperTest {

    @Test
    fun mapFromSearchResponseItemToSearchItem() {
        val expected = SearchItem(
            id = "1",
            name = "2",
        )
        val result = SearchResponseItem(
            id = "1",
            name = "2",
        ).toSearchItem()

        Assert.assertEquals(expected, result)
    }
}
