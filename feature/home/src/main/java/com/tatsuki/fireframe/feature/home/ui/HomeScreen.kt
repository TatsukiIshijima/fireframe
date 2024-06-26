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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tatsuki.fireframe.core.designsystem.component.ConfirmDialog
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.model.SlideGroup
import com.tatsuki.fireframe.feature.home.HomeViewModel
import com.tatsuki.fireframe.feature.home.R
import com.tatsuki.fireframe.feature.home.model.HomeState
import com.tatsuki.fireframe.feature.home.model.SourceType

@Composable
internal fun HomeRoute(
    onClickSetting: () -> Unit,
    onClickSource: (SourceType) -> Unit,
    onOpenSlideGroup: (SlideGroup) -> Unit,
    onClickSlideStart: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle(
        initialValue = HomeState.create(),
    )

    HomeScreen(
        homeState = homeState,
        modifier = modifier,
        onClickSetting = onClickSetting,
        onClickSource = { sourceType ->
            onClickSource(sourceType)
        },
        onSelectSlideGroup = homeViewModel::onSelectSlideGroup,
        onOpenSlideGroup = { slideGroup ->
            onOpenSlideGroup(slideGroup)
        },
        onDeleteSlideGroup = homeViewModel::onClickDeleteSlideGroupButton,
        onClickStartButton = onClickSlideStart,
    )

    if (homeState.deleteTargetSlideGroup != null) {
        ConfirmDialog(
            title = stringResource(
                id = R.string.delete_slide_group_confirm_dialog_title,
                homeState.deleteTargetSlideGroup?.groupName ?: "",
            ),
            positiveButtonText = stringResource(id = R.string.delete_button),
            negativeButtonText = stringResource(id = R.string.cancel_button),
            onDismissRequest = homeViewModel::onDeleteSlideGroupCancel,
            onConfirmation = homeViewModel::onDeleteSlideGroup,
        )
    }

    LaunchedEffect(Unit) {
        homeViewModel.onCreate()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun HomeScreen(
    homeState: HomeState,
    modifier: Modifier = Modifier,
    onClickSetting: () -> Unit = {},
    onClickSource: (SourceType) -> Unit = {},
    onSelectSlideGroup: (SlideGroup) -> Unit = {},
    onOpenSlideGroup: (SlideGroup) -> Unit = {},
    onDeleteSlideGroup: (SlideGroup) -> Unit = {},
    onClickStartButton: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            IconButton(
                onClick = onClickSetting,
                modifier = Modifier.align(Alignment.End),
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "setting",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 32.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displaySmall,
                fontFamily = FontFamily.Serif,
            )
            LazyColumn(
                modifier = Modifier.padding(16.dp),
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.select_image_label),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
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
                        homeState.sourceTypes.forEach {
                            SourceTypeItem(
                                sourceType = it,
                                contentDescription = "SourceCategory",
                                enable = homeState.isEnableSourceTypes,
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
                        text = stringResource(id = R.string.slideshow_group_label),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(homeState.slideshowGroups.size) {
                    val slideGroup = homeState.slideshowGroups[it]
                    SlideGroupItem(
                        slideGroup = slideGroup,
                        isSelected = homeState.isSelectedSlideGroup(slideGroup),
                        onSelectGroup = { selectSlideGroup ->
                            onSelectSlideGroup(selectSlideGroup)
                        },
                        onOpenGroup = { openSlideGroup ->
                            onOpenSlideGroup(openSlideGroup)
                        },
                        onDeleteGroup = { deleteSlideGroup ->
                            onDeleteSlideGroup(deleteSlideGroup)
                        },
                    )
                    if (it != MAX_SLIDE_GROUP_COUNT) {
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
            enable = homeState.isSelectedAnySlideGroup(),
        )
    }
}

@Composable
private fun StartSlideshowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (enable) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                        .copy(alpha = 0.12f)
                },
            )
            .clickable {
                if (!enable) return@clickable
                onClick()
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Play Arrow",
            tint = if (enable) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
                    .copy(alpha = 0.38f)
            },
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.start_slideshow_button),
            color = if (enable) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
                    .copy(alpha = 0.38f)
            },
            style = MaterialTheme.typography.bodyLarge,
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
        HomeScreen(
            homeState = HomeState.fake(),
        )
    }
}

@Preview
@Composable
private fun HomeScreenMobilePreview() {
    FireframeTheme {
        HomeScreen(
            homeState = HomeState.fake(),
        )
    }
}

private const val MAX_SLIDE_GROUP_COUNT = 10
