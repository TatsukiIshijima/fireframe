package com.tatsuki.fireframe.feature.onboarding.ui

import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tatsuki.fireframe.core.common.network.toContentUri
import com.tatsuki.fireframe.core.ui.AsyncImage
import com.tatsuki.fireframe.feature.onboarding.MediaPickerViewModel

@Composable
internal fun MediaPickerRoute(
    modifier: Modifier = Modifier,
    mediaPickerViewModel: MediaPickerViewModel = hiltViewModel(),
) {

    val imageUris = mediaPickerViewModel.images.collectAsState()

    MediaPickerScreen(
        onGrantedAllPermissions = mediaPickerViewModel::onGrantedReadExternalStoragePermission,
        imageUris = imageUris.value.map { it.id.toContentUri() },
        modifier = modifier,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun MediaPickerScreen(
    onGrantedAllPermissions: () -> Unit,
    imageUris: List<Uri>,
    modifier: Modifier = Modifier,
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

        onGrantedAllPermissions()

        if (imageUris.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                imageUris.forEach { uri ->
                    val thumbnail = if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                        LocalContext.current.contentResolver.loadThumbnail(
                            uri,
                            Size(320, 240),
                            null,
                        )
                    } else {
                        uri
                    }
                    AsyncImage(
                        model = thumbnail,
                        contentDescription = null,
                        modifier = Modifier.size(
                            width = 320.dp,
                            height = 240.dp,
                        ),
                    )
                }
            }
        } else {
            Text("No images found")
        }
    } else {
        Column {
            val textToShow = if (multiplePermissionState.shouldShowRationale) {
                // If the user has denied the permission but the rationale can be shown,
                // then gently explain why the app requires this permission
                "The read media is important for this app. Please grant the permission."
            } else {
                // If it's the first time the user lands on this feature, or the user
                // doesn't want to be asked again for this permission, explain that the
                // permission is required
                "Read media permission required for this feature to be available. " +
                    "Please grant the permission"
            }
            Text(textToShow)
            Button(onClick = { multiplePermissionState.launchMultiplePermissionRequest() }) {
                Text("Request permission")
            }
        }
    }
}
