package com.tatsuki.fireframe.feature.mediaselector.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tatsuki.fireframe.core.designsystem.component.TopAppBar
import com.tatsuki.fireframe.feature.mediaselector.MediaGalleryViewModel
import com.tatsuki.fireframe.feature.mediaselector.R
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableMediaImage
import com.tatsuki.fireframe.feature.mediaselector.ui.component.MediaGallery

@Composable
internal fun MediaGalleryRoute(
    modifier: Modifier = Modifier,
    mediaGalleryViewModel: MediaGalleryViewModel = hiltViewModel(),
) {
    val selectedImagesState by mediaGalleryViewModel.selectedImages.collectAsStateWithLifecycle()

    MediaGalleryScreen(
        mediaImages = selectedImagesState,
        modifier = modifier,
    )

    LaunchedEffect(Unit) {
        mediaGalleryViewModel.loadSelectedImages()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MediaGalleryScreen(
    mediaImages: List<SelectableMediaImage>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            titleRes = R.string.media_selector_title,
            modifier = Modifier,
        )
        MediaGallery(
            mediaImages = mediaImages,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview
@Composable
private fun MediaGalleryScreenPreview() {
    MediaGalleryScreen(
        mediaImages = listOf(
            SelectableMediaImage.fake(id = 0),
            SelectableMediaImage.fake(id = 1),
            SelectableMediaImage.fake(id = 2),
        ),
    )
}
