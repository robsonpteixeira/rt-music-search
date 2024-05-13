package com.robsonteixeira.musicsearch.features.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.robsonteixeira.musicsearch.features.search.presentation.SearchScreen

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchScreen(navigate: (String) -> Unit) {
    composable(SEARCH_ROUTE) {
        SearchScreen(navigate = navigate)
    }
}
