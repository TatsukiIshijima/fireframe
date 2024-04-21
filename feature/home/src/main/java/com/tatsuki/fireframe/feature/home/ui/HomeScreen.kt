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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
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
import com.tatsuki.fireframe.feature.home.model.SourceType

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    val sourceTypes = listOf<SourceType>(
        SourceType.LocalStorage(),
    )
    val slideGroups = listOf<String>(
        "SlideGroup1",
        "SlideGroup2",
        "SlideGroup3",
    )

    HomeScreen(
        sourceTypes = sourceTypes,
        slideGroups = slideGroups,
        modifier = modifier,
        onActionClick = {
            // TODO : Handle action click
        },
        onClickSource = { sourceType ->
            // TODO : Handle source type click
        },
        onSelectSlideGroup = { slideGroup ->
            // TODO : Handle select slide group
        },
        onOpenSlideGroup = { slideGroup ->
            // TODO : Handle click slide group
        },
        onDeleteSlideGroup = { slideGroup ->
            // TODO : Handle delete slide group
        },
        onClickStartButton = {
            // TODO : Handle start button click
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun HomeScreen(
    sourceTypes: List<SourceType>,
    slideGroups: List<String>,
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit = {},
    onClickSource: (SourceType) -> Unit = {},
    onSelectSlideGroup: (String) -> Unit = {},
    onOpenSlideGroup: (String) -> Unit = {},
    onDeleteSlideGroup: (String) -> Unit = {},
    onClickStartButton: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                titleRes = R.string.app_name,
                actionIcon = Icons.Default.Settings,
                actionIconDescription = "Settings",
                onActionClick = onActionClick,
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
                        sourceTypes.forEach {
                            SourceTypeItem(
                                sourceType = it,
                                contentDescription = "SourceCategory",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onClick = { sourceType ->
                                    onClickSource(sourceType)
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
                items(slideGroups.size) {
                    val slideGroup = slideGroups[it]
                    SlideGroupItem(
                        name = slideGroup,
                        isSelected = it == 0,
                        onSelectGroup = {
                            onSelectSlideGroup(it)
                        },
                        onOpenGroup = {
                            onOpenSlideGroup(it)
                        },
                        onDeleteGroup = {
                            onDeleteSlideGroup(it)
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
                onClickStartButton()
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            enable = true,
        )
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
            text = stringResource(id = R.string.start_slideshow_button),
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
    val sourceTypes = listOf<SourceType>(
        SourceType.LocalStorage(),
        SourceType.LocalStorage(),
    )
    val slideGroups = listOf<String>(
        "SlideGroup1",
        "SlideGroup2",
        "SlideGroup3",
    )

    FireframeTheme {
        HomeScreen(
            sourceTypes = sourceTypes,
            slideGroups = slideGroups,
        )
    }
}

@Preview
@Composable
private fun HomeScreenMobilePreview() {
    val sourceTypes = listOf<SourceType>(
        SourceType.LocalStorage(),
        SourceType.LocalStorage(),
        SourceType.LocalStorage(),
    )
    val slideGroups = listOf<String>(
        "SlideGroup1",
        "SlideGroup2",
        "SlideGroup3",
    )

    FireframeTheme {
        HomeScreen(
            sourceTypes = sourceTypes,
            slideGroups = slideGroups,
        )
    }
}
