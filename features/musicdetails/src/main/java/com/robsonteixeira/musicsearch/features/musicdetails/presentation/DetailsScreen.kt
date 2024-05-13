package com.robsonteixeira.musicsearch.features.musicdetails.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.robsonteixeira.musicsearch.R
import com.robsonteixeira.musicsearch.core.designlib.components.ErrorScreen
import com.robsonteixeira.musicsearch.core.designlib.components.LoadingScreen
import com.robsonteixeira.musicsearch.core.designlib.unit.Spaces.byte
import com.robsonteixeira.musicsearch.core.designlib.unit.Spaces.mega
import com.robsonteixeira.musicsearch.core.designlib.unit.Typography
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.model.DetailsItem
import com.robsonteixeira.musicsearch.features.musicdetails.presentation.DetailsViewModel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsScreen(
    navigateUp: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is DetailsViewModel.Effect.NavigateToSource -> uriHandler.openUri(it.src)
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.screen_details_topbar_title_text)) },
                navigationIcon = {
                        IconButton(onClick = navigateUp) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when(state.value) {
                UiState.Loading -> LoadingScreen()
                UiState.Error -> ErrorScreen(
                    text = stringResource(R.string.screen_details_error_title_text),
                    buttonText = stringResource(R.string.screen_details_error_button_text),
                    buttonClick = viewModel::search
                )
                is UiState.Loaded -> DetailsScreenContent(
                    details = (state.value as UiState.Loaded).item,
                    onClick = viewModel::onClick
                )
            }
        }
    }
}

@Composable
internal fun DetailsScreenContent(
    details: DetailsItem,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(byte),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (details.image.isNotBlank()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .padding(mega),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(details.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.screen_details_image_description_text),
                contentScale = ContentScale.Crop,
            )
        }
        Text(modifier = Modifier.padding(byte), text = details.name, style = Typography.titleText)
        if (details.src.isNotBlank()) {
            Button(
                modifier = Modifier.padding(byte),
                content = { Text(text = stringResource(R.string.screen_details_link_button_text))},
                onClick = { onClick(details.src) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun DetailsScreenContentPreview() = DetailsScreenContent(
    details = DetailsItem(id = "", name = "Music X", image = "", src = "source"),
    onClick = {}
)
