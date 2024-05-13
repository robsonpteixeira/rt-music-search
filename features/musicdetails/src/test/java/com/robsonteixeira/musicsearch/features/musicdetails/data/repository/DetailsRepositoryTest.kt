package com.robsonteixeira.musicsearch.features.musicdetails.data.repository

import com.robsonteixeira.musicsearch.features.musicdetails.data.api.DetailsApi
import com.robsonteixeira.musicsearch.features.musicdetails.data.api.model.Data
import com.robsonteixeira.musicsearch.features.musicdetails.data.api.model.DetailsResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

internal class DetailsRepositoryTest{

    private val detailsApi = mockk<DetailsApi>()

    private val repository = DetailsRepositoryImpl(detailsApi)

    @Test
    fun `given successful API response, when getting details, then returns Success`() = runTest {
        coEvery {
            detailsApi.getDetails(any(), any())
        } returns Response.success(
            DetailsResponse(
                Data(
                    id = "",
                    name = ""
                )
            )
        )

        val result = repository.getDetails("")

        assertTrue(result.isSuccess)
    }


    @Test
    fun `given unsuccessful API response, when getting details, then returns Failure`() = runTest {
        coEvery {
            detailsApi.getDetails(any(), any())
        } returns mockk<Response<DetailsResponse>> {
            every { isSuccessful } returns false
            every { errorBody() } returns null
        }

        val result = repository.getDetails("")

        assertTrue(result.isFailure)
    }
}
