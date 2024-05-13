package com.robsonteixeira.musicsearch.features.search.presentation

import app.cash.turbine.test
import com.robsonteixeira.musicsearch.features.search.data.repository.SearchRepository
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val searchRepository = mockk<SearchRepository>()

    private val viewModel = SearchViewModel(searchRepository)

    private val list = listOf(
        SearchItem(
            id = "",
            name = "",
        )
    )

    @Test
    fun `when search with empty query, receive empty state`() = runTest {
        viewModel.onQueryChanged("")

        val expected = SearchViewModel.UiState.Empty
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when search with repository success, receive loaded state`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.success(list)

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.UiState.Loaded(list)
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when search with repository success empty, receive empty state`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.success(listOf())

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.UiState.Empty
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when search with repository failure , receive error state`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.failure(Exception())

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.UiState.Error
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when receive onClick, send effect`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.success(list)

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.Effect.NavigateToDetails("")

        viewModel.effect.test {
            viewModel.onClick("")
            val item = awaitItem()
            Assert.assertEquals(expected, item)
        }
    }
}
