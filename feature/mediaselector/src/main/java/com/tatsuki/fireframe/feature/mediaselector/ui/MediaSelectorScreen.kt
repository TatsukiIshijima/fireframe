package com.tatsuki.fireframe.feature.mediaselector.ui

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tatsuki.fireframe.core.designsystem.component.TopAppBar
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.feature.mediaselector.MediaSelectorViewModel
import com.tatsuki.fireframe.feature.mediaselector.R
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaDirectory
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaImage
import com.tatsuki.fireframe.feature.mediaselector.ui.component.MediaGallery
import com.tatsuki.fireframe.feature.mediaselector.ui.component.MediaSelectorTabPager

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun MediaSelectorRoute(
    modifier: Modifier = Modifier,
    mediaSelectorViewModel: MediaSelectorViewModel = hiltViewModel(),
) {
    val needPermissions = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
        listOf(
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_VIDEO,
        )
    } else {
        listOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    }
    val multiplePermissionState = rememberMultiplePermissionsState(needPermissions)
    if (multiplePermissionState.allPermissionsGranted) {
        LaunchedEffect(Unit) {
            mediaSelectorViewModel.onGrantedReadExternalStoragePermission()
        }
        val directoriesState by mediaSelectorViewModel.imageDirectories.collectAsStateWithLifecycle()
        MediaSelectorScreen(
            directories = directoriesState,
            modifier = modifier,
            onSelect = mediaSelectorViewModel::onSelect,
            onCancel = {
                // TODO: Implement onCancel
            },
            onFinish = mediaSelectorViewModel::onFinish,
        )
    } else {
        if (multiplePermissionState.shouldShowRationale) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("The read media is important for this app. Please grant the permission.")
            }
        } else {
            LaunchedEffect(Unit) {
                multiplePermissionState.launchMultiplePermissionRequest()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun MediaSelectorScreen(
    directories: List<SelectableLocalMediaDirectory>,
    modifier: Modifier = Modifier,
    onSelect: (SelectableLocalMediaImage) -> Unit = {},
    onCancel: () -> Unit = {},
    onFinish: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            titleRes = R.string.media_selector_title,
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationIconDescription = "Back",
            onNavigationClick = onCancel,
        )
        MediaSelectorTabPager(
            tabNames = directories.map { it.name },
            modifier = Modifier.weight(1f),
            pageContent = { pageIndex ->
                val images = directories[pageIndex].selectableMediaImages
                if (images.isNotEmpty()) {
                    MediaGallery(
                        mediaImages = images,
                        onSelect = { mediaImage -> onSelect(mediaImage) },
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text("No images found")
                    }
                }
            },
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                .padding(16.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onFinish,
            ) {
                Text(text = stringResource(id = R.string.media_selector_ok_button))
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_TABLET,
)
@Composable
private fun MediaSelectorScreenTabletPreview() {
    FireframeTheme {
        MediaSelectorScreen(
            directories = listOf(
                SelectableLocalMediaDirectory.fake(
                    name = "Camera",
                ),
                SelectableLocalMediaDirectory.fake(
                    name = "Screenshots",
                ),
            ),
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun MediaSelectorScreenMobilePreview() {
    FireframeTheme {
        MediaSelectorScreen(
            directories = listOf(
                SelectableLocalMediaDirectory.fake(
                    name = "Camera",
                ),
                SelectableLocalMediaDirectory.fake(
                    name = "Screenshots",
                ),
            ),
        )
    }
}
