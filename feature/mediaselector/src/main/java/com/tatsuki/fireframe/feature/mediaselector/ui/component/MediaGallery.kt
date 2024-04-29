package com.tatsuki.fireframe.feature.mediaselector.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaImage

@Composable
internal fun MediaGallery(
    mediaImages: List<SelectableLocalMediaImage>,
    modifier: Modifier = Modifier,
    onSelect: (SelectableLocalMediaImage) -> Unit = {},
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
                    isSelected = image.isSelected.value,
                    onSelect = { mediaImage ->
                        onSelect(mediaImage)
                    },
                    modifier = Modifier.aspectRatio(1f),
                    contentScale = ContentScale.Crop,
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
            SelectableLocalMediaImage.fake(id = it.toLong())
        },
    )
}
