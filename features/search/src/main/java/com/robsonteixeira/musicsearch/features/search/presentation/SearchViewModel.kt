package com.robsonteixeira.musicsearch.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robsonteixeira.musicsearch.core.analytics.AnalyticsEventTracker
import com.robsonteixeira.musicsearch.features.search.data.repository.SearchRepository
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val analyticsEventTracker: AnalyticsEventTracker,
): ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Empty)
    val state = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    init {
        analyticsEventTracker.trackEvent(SearchAnalytics.getScreenView())
    }

    fun search() {
        if (_searchQuery.value.isBlank()) {
            setState(UiState.Empty)
            return
        }
        setState(UiState.Loading)
        viewModelScope.launch {
            searchRepository.getSearch(_searchQuery.value).runCatching {
                val list = getOrThrow()

                if (list.isEmpty()) {
                    setState(UiState.Empty)
                    return@launch
                }

                setState(UiState.Loaded(list.toImmutableList()))
            }.onFailure {
                setState(UiState.Error)
            }
        }
    }

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
        search()
    }

    fun onClick(id: String) {
        analyticsEventTracker.trackEvent(SearchAnalytics.getItemClick(id))
        viewModelScope.launch {
            _effect.emit(Effect.NavigateToDetails(id))
        }
    }

    private fun setState(updatedObject: UiState) {
        sendEventState(updatedObject)
        _state.value = updatedObject
    }

    private fun sendEventState(updatedObject: UiState) {
        analyticsEventTracker.trackEvent(
            when(updatedObject) {
                UiState.Empty -> SearchAnalytics.getEmpty()
                UiState.Error -> SearchAnalytics.getError()
                is UiState.Loaded -> SearchAnalytics.getLoaded()
                UiState.Loading -> SearchAnalytics.getLoading()
            }
        )
    }

    internal sealed class UiState {
        data object Loading : UiState()
        data object Error : UiState()
        data object Empty : UiState()
        data class Loaded(val list: ImmutableList<SearchItem>) : UiState()
    }

    internal sealed class Effect {
        data class NavigateToDetails(val id: String): Effect()
    }
}
