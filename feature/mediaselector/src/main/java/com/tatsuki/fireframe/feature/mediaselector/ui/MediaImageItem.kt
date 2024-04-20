package com.tatsuki.fireframe.feature.mediaselector.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.core.designsystem.component.AsyncImage
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.feature.mediaselector.R

@Composable
private fun MediaImageItem(
    mediaImage: MediaImage,
    model: Any?,
    contentDescription: String?,
    isSelected: Boolean,
    onSelect: (MediaImage) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    filterQuality: FilterQuality = FilterQuality.High,
) {
    Box(
        modifier = modifier,
    ) {
        val isLocalInspection = LocalInspectionMode.current
        if (isLocalInspection) {
            Placeholder(
                modifier = Modifier.fillMaxSize(),
                text = "Image",
            )
        } else {
            AsyncImage(
                model = model,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .clickable {
                        if (model == null) {
                            return@clickable
                        }
                        onSelect(mediaImage)
                    },
                placeholder = placeholder,
                error = error,
                contentScale = contentScale,
                filterQuality = filterQuality,
            )
        }

        if (isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_check_circle_24),
                contentDescription = null,
                modifier = Modifier
                    .sizeIn(minWidth = 24.dp, maxWidth = 48.dp)
                    .padding(4.dp)
                    .align(Alignment.TopEnd),
            )
        }
    }
}

@Preview
@Composable
private fun MediaImageItemPreview() {
    MediaImageItem(
        mediaImage = MediaImage(
            id = 0,
            name = "Image",
        ),
        model = "",
        contentDescription = "Image",
        modifier = Modifier.size(128.dp),
        isSelected = true,
        onSelect = {},
    )
}

@Composable
internal fun MediaImageItem(
    mediaImage: MediaImage,
    model: Any?,
    contentDescription: String?,
    onSelect: (MediaImage) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    filterQuality: FilterQuality = FilterQuality.High,
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    MediaImageItem(
        mediaImage = mediaImage,
        model = model,
        contentDescription = contentDescription,
        isSelected = isSelected,
        onSelect = {
            isSelected = !isSelected
            onSelect(it)
        },
        modifier = modifier,
        placeholder = placeholder,
        error = error,
        contentScale = contentScale,
        filterQuality = filterQuality,
    )
}
