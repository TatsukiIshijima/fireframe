package com.tatsuki.fireframe.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tatsuki.fireframe.feature.onboarding.ui.MediaPickerRoute

const val MEDIA_PICKER_ROUTE = "media_picker_route"

fun NavController.navigateToMediaPicker(navOptions: NavOptions? = null) {
    navigate(MEDIA_PICKER_ROUTE, navOptions)
}

fun NavGraphBuilder.mediaPickerScreen() {
    composable(route = MEDIA_PICKER_ROUTE) {
        MediaPickerRoute()
    }
}
