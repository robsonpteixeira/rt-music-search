package com.robsonteixeira.musicsearch.core.designlib.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.robsonteixeira.musicsearch.core.designlib.unit.Spaces.byte
import com.robsonteixeira.musicsearch.core.designlib.unit.Typography

@Composable
fun FeedbackScreen(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier = Modifier.padding(bottom = byte), text = text, style = Typography.titleText)
   }
}

@Preview(showBackground = true)
@Composable
internal fun FeedbackPreview() = FeedbackScreen(
    text = "Try another query.",
)
