package com.tatsuki.fireframe.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.core.designsystem.R

@Composable
fun IconPlaceholder(
    modifier: Modifier = Modifier,
    @DrawableRes resourceId: Int = R.drawable.outline_image_24,
    size: Dp = 24.dp,
) {
    Box(modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(id = resourceId),
            contentDescription = "Placeholder",
            modifier = Modifier
                .size(size)
                .align(Alignment.Center),
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}
