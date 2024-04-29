package com.tatsuki.fireframe.feature.mediaselector.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.tatsuki.fireframe.feature.mediaselector.model.MediaGalleryState
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaImage
import com.tatsuki.fireframe.feature.mediaselector.ui.component.MediaGallery

@Composable
internal fun MediaGalleryRoute(
    slideGroupId: Long,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    mediaGalleryViewModel: MediaGalleryViewModel = hiltViewModel(),
) {
    val mediaGalleryState by mediaGalleryViewModel.mediaGalleryState.collectAsStateWithLifecycle()

    MediaGalleryScreen(
        mediaGalleryState = mediaGalleryState,
        onBack = onBack,
        modifier = modifier,
    )

    LaunchedEffect(Unit) {
        mediaGalleryViewModel.loadSlideGroup(
            slideGroupId = slideGroupId,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MediaGalleryScreen(
    mediaGalleryState: MediaGalleryState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = mediaGalleryState.groupName,
            modifier = Modifier,
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationIconDescription = "Back",
            onNavigationClick = onBack,
        )
        MediaGallery(
            mediaImages = mediaGalleryState.images,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview
@Composable
private fun MediaGalleryScreenPreview() {
    MediaGalleryScreen(
        mediaGalleryState = MediaGalleryState(
            groupName = "GroupName",
            images = listOf(
                SelectableLocalMediaImage.fake(id = 0),
                SelectableLocalMediaImage.fake(id = 1),
                SelectableLocalMediaImage.fake(id = 2),
            ),
        ),
        onBack = {},
    )
}
