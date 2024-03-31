package com.tatsuki.fireframe.feature.slideshow.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SlideshowRoute(
    modifier: Modifier = Modifier,
) {
    SlideshowScreen(
        modifier = modifier,
    )
}

@Composable
internal fun SlideshowScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = "SlideshowScreen1")
        Text(text = "SlideshowScreen2")
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowScreenPreview() {
    SlideshowScreen()
}
