package com.tatsuki.fireframe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.tatsuki.fireframe.feature.mediaselector.navigation.mediaPickerScreen
import com.tatsuki.fireframe.ui.FireframeAppState

@Composable
fun FireframeNavHost(
    appState: FireframeAppState,
    modifier: Modifier = Modifier,
    startDestination: Destination = Destination.MediaPicker(),
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination.routeName,
        modifier = modifier,
    ) {
        mediaPickerScreen()
    }
}
