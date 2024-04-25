package com.tatsuki.fireframe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.tatsuki.fireframe.feature.home.navigation.homeScreen
import com.tatsuki.fireframe.feature.mediaselector.navigation.mediaGalleryScreen
import com.tatsuki.fireframe.feature.mediaselector.navigation.mediaSelectorScreen
import com.tatsuki.fireframe.feature.mediaselector.navigation.navigateToMediaGallery
import com.tatsuki.fireframe.feature.mediaselector.navigation.navigateToMediaSelector
import com.tatsuki.fireframe.feature.slideshow.navigation.navigateToSlideshow
import com.tatsuki.fireframe.feature.slideshow.navigation.slideshowScreen
import com.tatsuki.fireframe.ui.FireframeAppState

@Composable
fun FireframeNavHost(
    appState: FireframeAppState,
    modifier: Modifier = Modifier,
    startDestination: Destination = Destination.Home(),
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination.routeName,
        modifier = modifier,
    ) {
        homeScreen(
            onClickSource = { sourceType ->
                // TODO : Handle source type click
                navController.navigateToMediaSelector()
            },
            onOpenSlideGroup = { slideGroup ->
                // TODO : Handle open slide group
                navController.navigateToMediaGallery()
            },
            onClickSlideStart = navController::navigateToSlideshow,
        )
        mediaGalleryScreen()
        mediaSelectorScreen()
        slideshowScreen()
    }
}
