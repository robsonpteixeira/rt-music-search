package com.robsonteixeira.musicsearch.features.musicdetails.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.robsonteixeira.musicsearch.core.analytics.AnalyticsEventTracker
import com.robsonteixeira.musicsearch.core.analytics.model.AnalyticsEvent
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.DetailsRepository
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem
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

private const val SCREEN_NAME = "details_screen"

@OptIn(ExperimentalCoroutinesApi::class)
internal class DetailsViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val detailsRepository = mockk<DetailsRepository>()

    private val analyticsEventTracker = mockk<AnalyticsEventTracker> {
        every { trackEvent(any()) } returns Unit
    }

    private val savedStateHandle = mockk<SavedStateHandle> {
        every { get<String>("id") } returns "0"
    }

    private lateinit var viewModel: DetailsViewModel

    private val details = DetailsItem(
        id = "",
        name = "",
        image = "",
        src = "",
    )

    @Test
    fun `when init viewModel without id, receive error state`() = runTest {
        every { savedStateHandle.get<String>("id") } returns ""
        initViewModel()

        val expected = DetailsViewModel.UiState.Error
        Assert.assertEquals(expected, viewModel.state.value)
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "error"))
        }
    }

    @Test
    fun `when init viewModel with repository success, receive loaded state`() = runTest {
        coEvery { detailsRepository.getDetails(any()) } returns Result.success(details)

        initViewModel()

        val expected = DetailsViewModel.UiState.Loaded(details)
        Assert.assertEquals(expected, viewModel.state.value)
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "loaded"))
        }
    }

    @Test
    fun `when init viewModel with repository failure, receive error state`() = runTest {
        coEvery { detailsRepository.getDetails(any()) } returns Result.failure(Exception())

        initViewModel()

        val expected = DetailsViewModel.UiState.Error
        Assert.assertEquals(expected, viewModel.state.value)
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "error"))
        }
    }

    @Test
    fun `when receive onClick, send effect`() = runTest {
        coEvery { detailsRepository.getDetails(any()) } returns Result.success(details)

        initViewModel()

        val expected = DetailsViewModel.Effect.NavigateToSource("aa")

        viewModel.effect.test {
            viewModel.onClick("aa")
            val item  = awaitItem()
            Assert.assertEquals(expected, item)
        }
        verify {
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "screen_view"))
            analyticsEventTracker.trackEvent(AnalyticsEvent(SCREEN_NAME, "src:aa"))
        }
    }

    private fun initViewModel() {
        viewModel = DetailsViewModel(detailsRepository, analyticsEventTracker, savedStateHandle)
    }
}
