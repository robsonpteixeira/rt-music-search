package com.robsonteixeira.musicsearch.features.musicdetails.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.DetailsRepository
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem
import io.mockk.coEvery
import io.mockk.every
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
    }

    @Test
    fun `when init viewModel with repository success, receive loaded state`() = runTest {
        coEvery { detailsRepository.getDetails(any()) } returns Result.success(details)

        initViewModel()

        val expected = DetailsViewModel.UiState.Loaded(details)
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when init viewModel with repository failure, receive error state`() = runTest {
        coEvery { detailsRepository.getDetails(any()) } returns Result.failure(Exception())

        initViewModel()

        val expected = DetailsViewModel.UiState.Error
        Assert.assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when receive onClick, send effect`() = runTest {
        coEvery { detailsRepository.getDetails(any()) } returns Result.success(details)

        initViewModel()

        val expected = DetailsViewModel.Effect.NavigateToSource("")

        viewModel.effect.test {
            viewModel.onClick("")
            val item  = awaitItem()
            Assert.assertEquals(expected, item)
        }
    }

    private fun initViewModel() {
        viewModel = DetailsViewModel(detailsRepository, savedStateHandle)
    }
}
