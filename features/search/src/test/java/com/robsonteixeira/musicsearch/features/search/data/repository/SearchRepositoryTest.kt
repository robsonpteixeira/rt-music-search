package com.robsonteixeira.musicsearch.features.search.data.repository

import com.robsonteixeira.musicsearch.features.search.data.api.SearchApi
import com.robsonteixeira.musicsearch.features.search.data.api.model.SearchResponseItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

internal class SearchRepositoryTest {

    private val searchApi = mockk<SearchApi>()

    private val repository = SearchRepositoryImpl(searchApi)

    @Test
    fun `given successful API response, when searching, then returns Success`() = runTest {
        coEvery {
            searchApi.getSearch(any(), any())
        } returns Response.success(
            listOf(
                SearchResponseItem(
                    id = "",
                    name = "",
                )
            )
        )

        val result = repository.getSearch("")

        Assert.assertTrue(result.isSuccess)
    }


    @Test
    fun `given unsuccessful API response, when searching, then returns Failure`() = runTest {
        coEvery {
            searchApi.getSearch(any(), any())
        } returns mockk<Response<List<SearchResponseItem>>> {
            every { isSuccessful } returns false
            every { errorBody() } returns null
        }

        val result = repository.getSearch("")

        Assert.assertTrue(result.isFailure)
    }
}
