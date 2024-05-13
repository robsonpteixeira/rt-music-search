package com.robsonteixeira.musicsearch.features.musicdetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.DetailsRepository
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem
import com.robsonteixeira.musicsearch.features.musicdetails.navigation.ARG_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val id = savedStateHandle.get<String>(ARG_ID).orEmpty()

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    init {
        search()
    }

    fun search() {
        if (id.isBlank()) {
            setState(UiState.Error)
            return
        }
        viewModelScope.launch {
            detailsRepository.getDetails(id).runCatching {
                val details = getOrThrow()
                setState(UiState.Loaded(details))
            }.onFailure {
                setState(UiState.Error)
            }
        }
    }

    fun onClick(src: String) {
        viewModelScope.launch {
            _effect.emit(Effect.NavigateToSource(src))
        }
    }

    private fun setState(updatedObject: UiState) {
        _state.value = updatedObject
    }

    internal sealed class UiState {
        data object Loading : UiState()
        data object Error : UiState()
        data class Loaded(val item: DetailsItem) : UiState()
    }

    internal sealed class Effect {
        data class NavigateToSource(val src: String): Effect()
    }
}
