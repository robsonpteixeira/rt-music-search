package com.robsonteixeira.musicsearch.core.designlib.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
   Box(
       modifier = modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
   ) {
       CircularProgressIndicator(
           color = MaterialTheme.colorScheme.primary
       )
   }
}

@Preview(showBackground = true)
@Composable
internal fun LoadingPreview() = LoadingScreen()
