package com.tatsuki.fireframe.feature.mediaselector.ui

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.feature.mediaselector.MediaSelectorViewModel
import com.tatsuki.fireframe.feature.mediaselector.R
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableMediaImage
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableMediaImageDirectory
import kotlinx.coroutines.launch
import com.tatsuki.fireframe.core.designsystem.R as designSystemR

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MediaSelectorScreen(
    directories: List<SelectableMediaImageDirectory>,
    modifier: Modifier = Modifier,
    onSelect: (SelectableMediaImage) -> Unit = {},
    onCancel: () -> Unit = {},
    onFinish: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        // FIXME: change not recomposition
        MediaSelectorTabPager(
            tabNames = directories.map { it.name },
            modifier = Modifier.fillMaxSize(),
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onCancel,
            ) {
                Text(text = stringResource(id = R.string.media_selector_cancel_button))
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp),
            )
            Button(
                modifier = Modifier.weight(1f),
                onClick = onFinish,
            ) {
                Text(text = stringResource(id = R.string.media_selector_ok_button))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MediaSelectorTabPager(
    tabNames: List<String>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState { tabNames.size },
    pageContent: @Composable PagerScope.(page: Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MediaSelectorTab(
            tabNames = tabNames,
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            onTabClick = { index ->
                Log.d("MediaSelectorScreen", "onTabClick: $index")
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { pageIndex ->
            pageContent(pageIndex)
        }
    }
}

@Composable
private fun MediaSelectorTab(
    tabNames: List<String>,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    onTabClick: (Int) -> Unit = {},
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
    ) {
        tabNames.forEachIndexed { index, name ->
            Tab(
                text = {
                    Text(name)
                },
                selected = selectedTabIndex == index,
                onClick = {
                    onTabClick(index)
                },
            )
        }
    }
}

@Composable
private fun MediaGallery(
    mediaImages: List<SelectableMediaImage>,
    modifier: Modifier = Modifier,
    onSelect: (SelectableMediaImage) -> Unit = {},
    state: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = modifier.fillMaxSize(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
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
                    placeholder = painterResource(id = designSystemR.drawable.outline_image_24),
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.Low,
                )
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
                SelectableMediaImageDirectory.fake(
                    name = "Camera",
                ),
                SelectableMediaImageDirectory.fake(
                    name = "Screenshots",
                ),
            ),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun MediaSelectorScreenMobilePreview() {
    FireframeTheme {
        MediaSelectorScreen(
            directories = listOf(
                SelectableMediaImageDirectory.fake(
                    name = "Camera",
                ),
                SelectableMediaImageDirectory.fake(
                    name = "Screenshots",
                ),
            ),
        )
    }
}
