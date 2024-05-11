package com.tatsuki.fireframe.feature.slideshow.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tatsuki.fireframe.feature.slideshow.ui.SlideshowRoute

const val SLIDESHOW_ROUTE = "slide_show_route"

fun NavController.navigateToSlideshow(navOptions: NavOptions? = null) {
    navigate(SLIDESHOW_ROUTE, navOptions)
}

fun NavGraphBuilder.slideshowScreen(
    isEnableWeather: Boolean,
) {
    composable(route = SLIDESHOW_ROUTE) {
        SlideshowRoute(
            isEnableWeather = isEnableWeather,
        )
    }
}
