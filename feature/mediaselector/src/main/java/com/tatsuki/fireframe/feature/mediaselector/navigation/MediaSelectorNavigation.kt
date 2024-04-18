package com.tatsuki.fireframe.feature.mediaselector.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tatsuki.fireframe.feature.mediaselector.ui.MediaSelectorRoute

const val MEDIA_SELECTOR_ROUTE = "media_selector_route"

fun NavController.navigateToMediaSelector(navOptions: NavOptions? = null) {
    navigate(MEDIA_SELECTOR_ROUTE, navOptions)
}

fun NavGraphBuilder.mediaSelectorScreen() {
    composable(route = MEDIA_SELECTOR_ROUTE) {
        MediaSelectorRoute()
    }
}
