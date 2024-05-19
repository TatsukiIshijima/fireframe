package com.tatsuki.fireframe.feature.setting.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tatsuki.fireframe.core.designsystem.component.TopAppBar
import com.tatsuki.fireframe.feature.setting.R

@Composable
internal fun SettingRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SettingScreen(
        onBack = onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingScreen(
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
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        onBack = {},
    )
}