package com.robsonteixeira.musicsearch.core.designlib.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.robsonteixeira.musicsearch.core.designlib.unit.Spaces.byte
import com.robsonteixeira.musicsearch.core.designlib.unit.Typography

@Composable
fun ErrorScreen(
    text: String,
    buttonText: String,
    buttonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier = Modifier.padding(bottom = byte), text = text, style = Typography.titleText)
        Button(
           content = { Text(text = buttonText) },
           onClick = buttonClick
        )
   }
}

@Preview(showBackground = true)
@Composable
internal fun ErrorPreview() = ErrorScreen(
    text = "Something went wrong.",
    buttonText = "Try again",
    buttonClick = {}
)
