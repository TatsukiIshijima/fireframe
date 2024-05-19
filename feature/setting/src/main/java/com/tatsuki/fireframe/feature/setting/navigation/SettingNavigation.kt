package com.tatsuki.fireframe.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tatsuki.fireframe.feature.setting.ui.SettingRoute

const val SETTING_ROUTE = "setting"

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    navigate(SETTING_ROUTE, navOptions)
}

fun NavGraphBuilder.settingScreen(
    onBack: () -> Unit,
) {
    composable(route = SETTING_ROUTE) {
        SettingRoute(
            onBack = onBack,
        )
    }
}