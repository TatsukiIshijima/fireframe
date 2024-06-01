package com.tatsuki.fireframe.feature.setting.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tatsuki.fireframe.core.designsystem.component.RadioButtonsDialog
import com.tatsuki.fireframe.core.designsystem.component.TopAppBar
import com.tatsuki.fireframe.feature.setting.R
import com.tatsuki.fireframe.feature.setting.SettingViewModel
import com.tatsuki.fireframe.feature.setting.model.ContentScaleType
import com.tatsuki.fireframe.feature.setting.model.SettingState
import com.tatsuki.fireframe.feature.setting.model.SlideshowInterval

@Composable
internal fun SettingRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel = hiltViewModel(),
) {
    val settingState by settingViewModel.settingState.collectAsStateWithLifecycle(
        initialValue = SettingState.create(),
    )
    SettingScreen(
        settingState = settingState,
        onClickSlideshowSetting = settingViewModel::onShowSlideshowIntervalSettingDialog,
        onClickContentScaleTypeSetting = settingViewModel::onShowContentScaleTypeSettingDialog,
        onBack = onBack,
        modifier = modifier,
    )

    if (settingState.shouldShowSlideshowIntervalSettingDialog) {
        RadioButtonsDialog(
            title = stringResource(id = R.string.slideshow_interval),
            items = SlideshowInterval.all(),
            selectedItem = settingState.slideshowInterval,
            onSelectItem = { selectableItem ->
                if (selectableItem !is SlideshowInterval) return@RadioButtonsDialog
                settingViewModel.onUpdateSlideshowInterval(selectableItem)
                settingViewModel.onDismissSlideshowIntervalSettingDialog()
            },
            cancelButtonText = stringResource(id = R.string.cancel),
            onDismissRequest = settingViewModel::onDismissSlideshowIntervalSettingDialog,
        )
    }

    if (settingState.shouldShowContentScaleTypeSettingDialog) {
        RadioButtonsDialog(
            title = stringResource(id = R.string.content_scale_type),
            items = ContentScaleType.all(),
            selectedItem = settingState.contentScaleType,
            onSelectItem = { selectableItem ->
                if (selectableItem !is ContentScaleType) return@RadioButtonsDialog
                settingViewModel.onUpdateContentScaleType(selectableItem)
                settingViewModel.onDismissContentScaleTypeSettingDialog()
            },
            cancelButtonText = stringResource(id = R.string.cancel),
            onDismissRequest = settingViewModel::onDismissContentScaleTypeSettingDialog,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingScreen(
    settingState: SettingState,
    onClickSlideshowSetting: () -> Unit,
    onClickContentScaleTypeSetting: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        TopAppBar(
            title = stringResource(id = R.string.setting_title),
            modifier = Modifier,
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationIconDescription = "Back",
            onNavigationClick = onBack,
        )
        SettingItem(
            title = stringResource(id = R.string.slideshow_interval),
            value = stringResource(id = settingState.slideshowInterval.nameResource),
            onClick = onClickSlideshowSetting,
        )
        SettingItem(
            title = stringResource(id = R.string.content_scale_type),
            value = stringResource(id = settingState.contentScaleType.nameResource),
            onClick = onClickContentScaleTypeSetting,
        )
        SettingItem(
            title = stringResource(id = R.string.app_version),
            value = "1.0.2",
        )
    }
}

@Composable
private fun SettingItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) {
        modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(16.dp)
    } else {
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    }
    Row(
        modifier = clickableModifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        settingState = SettingState.create(),
        onClickSlideshowSetting = {},
        onClickContentScaleTypeSetting = {},
        onBack = {},
    )
}
