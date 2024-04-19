package com.tatsuki.fireframe.feature.mediaselector.ui

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tatsuki.fireframe.core.common.toThumbnail
import com.tatsuki.fireframe.core.designsystem.component.AsyncImage
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory
import com.tatsuki.fireframe.feature.mediaselector.MediaSelectorViewModel
import kotlinx.coroutines.launch

@Composable
internal fun MediaSelectorRoute(
    modifier: Modifier = Modifier,
    mediaPickerViewModel: MediaSelectorViewModel = hiltViewModel(),
) {
    val directoriesState = mediaPickerViewModel.imageDirectories.collectAsState()

    MediaSelectorScreen(
        onGrantedAllPermissions = mediaPickerViewModel::onGrantedReadExternalStoragePermission,
        directories = directoriesState.value,
        modifier = modifier,
    )
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun MediaSelectorScreen(
    onGrantedAllPermissions: () -> Unit,
    directories: List<MediaImageDirectory>,
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
        LaunchedEffect(Unit) {
            onGrantedAllPermissions()
        }
        // FIXME: change not recomposition
        MediaSelectorTabPager(
            tabNames = directories.map { it.name },
            modifier = modifier,
            pageContent = { pageIndex ->
                val images = directories[pageIndex].images
                if (images.isNotEmpty()) {
                    MediaGallery(mediaImages = images)
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text("No images found")
                    }
                }
            },
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
                Log.d("MediaSelectorTabPager", "onTabClick: $index")
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
    mediaImages: List<MediaImage>,
    modifier: Modifier = Modifier,
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
            val isLocalInspection = LocalInspectionMode.current
            if (isLocalInspection) {
                Placeholder(
                    text = "Image$it",
                )
                return@items
            }

            val image = mediaImages[it]
            val thumbnail = LocalContext.current.toThumbnail(image.id)
            AsyncImage(
                model = thumbnail,
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f),
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.Low,
                placeHolder = {
                    Placeholder(
                        text = "Image$it",
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    showBackground = true,
    device = Devices.PIXEL_TABLET,
)
@Composable
private fun MediaSelectorTabPagerTabletPreview() {
    FireframeTheme {
        MediaSelectorTabPager(
            tabNames = listOf(
                "Tab1",
                "Tab2",
            ),
            pageContent = {
                val images = (0..10).map {
                    MediaImage(
                        id = it.toLong(),
                        name = "image$it",
                    )
                }
                MediaGallery(
                    mediaImages = images,
                )
            },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun MediaSelectorTabPagerMobilePreview() {
    FireframeTheme {
        MediaSelectorTabPager(
            tabNames = listOf(
                "Tab1",
                "Tab2",
            ),
            pageContent = {
                val images = (0..10).map {
                    MediaImage(
                        id = it.toLong(),
                        name = "image$it",
                    )
                }
                MediaGallery(
                    mediaImages = images,
                )
            },
        )
    }
}
