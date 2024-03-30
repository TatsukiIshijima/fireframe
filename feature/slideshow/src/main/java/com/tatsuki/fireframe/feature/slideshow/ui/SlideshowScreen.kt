package com.tatsuki.fireframe.feature.slideshow.ui

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
    Text(text = "SlideshowScreen")
}

@Preview(showBackground = true)
@Composable
fun SlideshowScreenPreview() {
    SlideshowScreen()
}
