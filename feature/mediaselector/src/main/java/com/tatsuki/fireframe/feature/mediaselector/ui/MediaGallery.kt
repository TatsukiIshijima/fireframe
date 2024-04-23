package com.tatsuki.fireframe.feature.mediaselector.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.core.designsystem.R.drawable
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableMediaImage

@Composable
internal fun MediaGallery(
    mediaImages: List<SelectableMediaImage>,
    modifier: Modifier = Modifier,
    onSelect: (SelectableMediaImage) -> Unit = {},
    state: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = Adaptive(128.dp),
        modifier = modifier.fillMaxSize(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(mediaImages.size) {
            val image = mediaImages[it]
            val isLocalInspection = LocalInspectionMode.current
            if (isLocalInspection) {
                Placeholder(
                    text = "Image$it",
                )
            } else {
                MediaImageItem(
                    mediaImage = image,
                    contentDescription = null,
                    onSelect = { mediaImage -> onSelect(mediaImage) },
                    modifier = Modifier.aspectRatio(1f),
                    placeholder = painterResource(id = drawable.outline_image_24),
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.Low,
                )
            }
        }
    }
}

@Preview
@Composable
private fun MediaGalleryPreview() {
    MediaGallery(
        mediaImages = List(10) {
            SelectableMediaImage(
                id = it.toLong(),
                uri = Uri.EMPTY,
                isSelected = mutableStateOf(false),
            )
        },
    )
}
