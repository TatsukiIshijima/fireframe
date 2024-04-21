package com.tatsuki.fireframe.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatsuki.fireframe.core.designsystem.component.TopAppBar
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.feature.home.R

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                titleRes = R.string.app_name,
                actionIcon = Icons.Default.Settings,
                actionIconDescription = "Settings",
                onActionClick = {
                    // TODO : Handle action click
                },
            )
            LazyColumn(
                modifier = Modifier.padding(16.dp),
            ) {
                item {
                    Text(
                        text = "画像選択",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                // FlowRow or FlowColumn
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        maxItemsInEachRow = 2,
                    ) {
                        (0..2).forEach {
                            SourceCategoryItem(
                                name = "Category$it",
                                imageVector = Icons.Default.Star,
                                contentDescription = "SourceCategory",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onClick = {
                                    // TODO : Handle category click
                                },
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
                item {
                    Text(
                        text = "スライドセット",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items((0..9).count()) {
                    SlideGroupItem(
                        name = "SlideGroup$it",
                        isSelected = false,
                        onSelect = {
                            // TODO : Handle select slide group
                        },
                        onClick = {
                            // TODO : Handle click slide group
                        },
                        onDelete = {
                            // TODO : Handle delete slide group
                        },
                    )
                    if (it != 9) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
        StartSlideshowButton(
            onClick = {
                // TODO : Handle start slideshow button click
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            enable = true,
        )
    }
}

@Composable
private fun SourceCategoryGallery(
    categories: List<String>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {},
    state: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(categories.size) {
            SourceCategoryItem(
                name = categories[it],
                imageVector = Icons.Default.Star,
                contentDescription = "SourceCategory",
                onClick = onClick,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SlideGroupList(
    slideGroups: List<String>,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit = {},
    onClick: (String) -> Unit = {},
    onDelete: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(slideGroups.size) {
            SlideGroupItem(
                name = slideGroups[it],
                isSelected = false,
                onSelect = onSelect,
                onClick = onClick,
                onDelete = onDelete,
            )
        }
    }
}

@Composable
private fun StartSlideshowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    enable: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color)
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Play Arrow",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.home_start_slideshow_button),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_TABLET,
)
@Composable
private fun HomeScreenTabletPreview() {
    FireframeTheme {
        HomeScreen()
    }
}

@Preview
@Composable
private fun HomeScreenMobilePreview() {
    FireframeTheme {
        HomeScreen()
    }
}
