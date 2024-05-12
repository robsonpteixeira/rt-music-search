package com.robsonteixeira.musicsearch.core.designlib.unit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

object Typography {
    private val defaultText
        @ReadOnlyComposable
        @Composable
        get() = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
    val titleText
        @ReadOnlyComposable
        @Composable
        get() = defaultText.copy(
            fontSize = 22.sp
        )
    val bodyText
        @ReadOnlyComposable
        @Composable
        get() = defaultText
}
