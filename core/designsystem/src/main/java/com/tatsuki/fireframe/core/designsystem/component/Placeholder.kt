package com.tatsuki.fireframe.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tatsuki.fireframe.core.designsystem.theme.backgroundLight

@Composable
fun Placeholder(
    modifier: Modifier = Modifier,
    backgroundColor: Color = backgroundLight,
    text: String = "Placeholder",
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(text)
    }
}
