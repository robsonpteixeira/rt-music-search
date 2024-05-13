package com.robsonteixeira.musicsearch.features.musicdetails.data.repository.mapper

import com.robsonteixeira.musicsearch.features.musicdetails.data.api.model.Data
import com.robsonteixeira.musicsearch.features.musicdetails.data.api.model.DetailsResponse
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem
import org.junit.Assert
import org.junit.Test

internal class DetailsItemMapper {

    @Test
    fun mapFromDetailsItemMapperToDetailsItem() {
        val expected = DetailsItem(
            id = "1",
            name = "2",
            image = "",
            src = ""
        )
        val result = DetailsResponse(
            Data(
                id = "1",
                name = "2",
            )
        ).toDetailsItem()

        Assert.assertEquals(expected, result)
    }
}