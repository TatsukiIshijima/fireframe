package com.tatsuki.fireframe.feature.mediaselector.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatsuki.fireframe.core.common.toThumbnail
import com.tatsuki.fireframe.core.designsystem.component.FireframeAsyncImage
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.feature.mediaselector.R
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaImage

@Composable
internal fun MediaImageItem(
    mediaImage: SelectableLocalMediaImage,
    contentDescription: String?,
    isSelected: Boolean,
    onSelect: (SelectableLocalMediaImage) -> Unit,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(
        modifier = modifier
            .clickable { onSelect(mediaImage) },
    ) {
        val isLocalInspection = LocalInspectionMode.current
        if (isLocalInspection) {
            Placeholder(
                modifier = Modifier.fillMaxSize(),
                text = "Image",
            )
        } else {
            val thumbnail = LocalContext.current.toThumbnail(mediaImage.id)
            FireframeAsyncImage(
                model = thumbnail,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = contentScale,
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
        mediaImage = SelectableLocalMediaImage.fake(),
        contentDescription = "Image",
        modifier = Modifier.size(128.dp),
        isSelected = true,
        onSelect = {},
    )
}
