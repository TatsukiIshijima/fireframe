package com.tatsuki.fireframe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import com.tatsuki.fireframe.feature.home.navigation.homeScreen
import com.tatsuki.fireframe.feature.mediaselector.navigation.mediaGalleryScreen
import com.tatsuki.fireframe.feature.mediaselector.navigation.mediaSelectorScreen
import com.tatsuki.fireframe.feature.mediaselector.navigation.navigateToMediaGallery
import com.tatsuki.fireframe.feature.mediaselector.navigation.navigateToMediaSelector
import com.tatsuki.fireframe.feature.setting.navigation.navigateToSetting
import com.tatsuki.fireframe.feature.setting.navigation.settingScreen
import com.tatsuki.fireframe.feature.slideshow.navigation.navigateToSlideshow
import com.tatsuki.fireframe.feature.slideshow.navigation.slideshowScreen
import com.tatsuki.fireframe.ui.FireframeAppState

@Composable
fun FireframeNavHost(
    appState: FireframeAppState,
    remoteConfig: FireframeRemoteConfig,
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
            onClickSetting = navController::navigateToSetting,
            onClickSource = { sourceType ->
                // TODO : Handle source type click
                navController.navigateToMediaSelector()
            },
            onOpenSlideGroup = navController::navigateToMediaGallery,
            onClickSlideStart = navController::navigateToSlideshow,
        )
        mediaGalleryScreen(
            onBack = navController::popBackStack,
        )
        mediaSelectorScreen(
            onBack = navController::popBackStack,
        )
        settingScreen(
            onBack = navController::popBackStack,
        )
        slideshowScreen(
            isEnableWeather = remoteConfig.isEnableWeather(),
        )
    }
}
