package com.robsonteixeira.musicsearch.features.musicdetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.robsonteixeira.musicsearch.features.musicdetails.presentation.DetailsScreen

const val DETAILS_ROUTE = "details/{id}"
internal const val ARG_ID = "id"

fun NavGraphBuilder.detailsScreen(navigateUp: () -> Unit) {
    composable(
        DETAILS_ROUTE,
        arguments = listOf(
            navArgument(ARG_ID) {
                type = NavType.StringType
            }
        )
    ) {
        DetailsScreen(navigateUp = navigateUp)
    }
}
