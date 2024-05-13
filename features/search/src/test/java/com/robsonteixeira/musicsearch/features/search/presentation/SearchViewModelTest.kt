package com.robsonteixeira.musicsearch.features.search.presentation

import app.cash.turbine.test
import com.robsonteixeira.musicsearch.core.analytics.AnalyticsEventTracker
import com.robsonteixeira.musicsearch.core.analytics.model.AnalyticsEvent
import com.robsonteixeira.musicsearch.features.search.data.repository.SearchRepository
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private const val SCREEN_NAME = "search_screen"

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

    private val analyticsEventTracker = mockk<AnalyticsEventTracker> {
        every { trackEvent(any()) } returns Unit
    }

    private val viewModel = SearchViewModel(searchRepository, analyticsEventTracker)

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
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "empty"))
        }
    }

    @Test
    fun `when search with repository success, receive loaded state`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.success(list)

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.UiState.Loaded(list)
        Assert.assertEquals(expected, viewModel.state.value)
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "loaded"))
        }
    }

    @Test
    fun `when search with repository success empty, receive empty state`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.success(listOf())

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.UiState.Empty
        Assert.assertEquals(expected, viewModel.state.value)
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "empty"))
        }
    }

    @Test
    fun `when search with repository failure , receive error state`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.failure(Exception())

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.UiState.Error
        Assert.assertEquals(expected, viewModel.state.value)
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "error"))
        }
    }

    @Test
    fun `when receive onClick, send effect`() = runTest {
        coEvery { searchRepository.getSearch(any()) } returns Result.success(list)

        viewModel.onQueryChanged("0")

        val expected = SearchViewModel.Effect.NavigateToDetails("aa")

        viewModel.effect.test {
            viewModel.onClick("aa")
            val item = awaitItem()
            Assert.assertEquals(expected, item)
        }
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "item_click:aa"))
        }
    }
}
