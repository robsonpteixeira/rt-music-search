package com.robsonteixeira.musicsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.robsonteixeira.musicsearch.features.musicdetails.navigation.detailsScreen
import com.robsonteixeira.musicsearch.features.search.navigation.SEARCH_ROUTE
import com.robsonteixeira.musicsearch.features.search.navigation.searchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainNavigation() }
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost( navController = navController, startDestination = SEARCH_ROUTE) {
        searchScreen(navigate = navController::navigate)
        detailsScreen(navigateUp = navController::navigateUp)
    }
}
