package com.robsonteixeira.musicsearch.features.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.robsonteixeira.musicsearch.R
import com.robsonteixeira.musicsearch.core.designlib.components.ErrorScreen
import com.robsonteixeira.musicsearch.core.designlib.components.FeedbackScreen
import com.robsonteixeira.musicsearch.core.designlib.components.LoadingScreen
import com.robsonteixeira.musicsearch.core.designlib.unit.Spaces.byte
import com.robsonteixeira.musicsearch.core.designlib.unit.Typography
import com.robsonteixeira.musicsearch.features.search.presentation.SearchViewModel.UiState
import com.robsonteixeira.musicsearch.features.search.data.repository.model.SearchItem

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is SearchViewModel.Effect.NavigateToDetails -> navigate("details/${it.id}")
            }
        }
    }

    SearchScreenContent(
        searchQuery = searchQuery.value,
        state = state.value,
        onQueryChanged = viewModel::onQueryChanged,
        onRefresh = viewModel::search,
        onClick = viewModel::onClick,
    )
}

@Composable
internal fun SearchScreenContent(
    searchQuery: String,
    state: UiState,
    onQueryChanged: (String) -> Unit,
    onRefresh: () -> Unit,
    onClick: (String) -> Unit,
) {
    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
        ){
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(byte),
                    value = searchQuery,
                    onValueChange = onQueryChanged,
                    label = { Text(text = stringResource(R.string.screen_search_query_text)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                )
            }
        }
        when(state) {
            UiState.Loading -> LoadingScreen()
            UiState.Empty -> FeedbackScreen(text = stringResource(R.string.screen_search_empty_text))
            UiState.Error -> ErrorScreen(
                text = stringResource(R.string.screen_search_error_title_text),
                buttonText = stringResource(R.string.screen_search_error_button_text),
                buttonClick = onRefresh
            )
            is UiState.Loaded -> {
                LazyColumn {
                    itemsIndexed(
                        items = state.list
                    ) { _, item ->
                        Text(modifier = Modifier
                            .padding(byte)
                            .fillMaxWidth()
                            .clickable(onClick = { onClick(item.id) }),
                            text = item.name,
                            style = Typography.bodyText
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun SearchScreenContentPreview() = SearchScreenContent(
    state = UiState.Loaded(
        list = listOf(
            SearchItem(id = "", name = "Music X"),
            SearchItem(id = "", name = "Music Y"),
            SearchItem(id = "", name = "Music Z"),
        )
    ),
    searchQuery = "Music",
    onQueryChanged = {},
    onRefresh = {},
    onClick = {},
)
