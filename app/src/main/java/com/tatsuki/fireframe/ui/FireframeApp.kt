package com.tatsuki.fireframe.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import com.tatsuki.fireframe.navigation.FireframeNavHost

@Composable
fun FireframeApp(
    appState: FireframeAppState,
    remoteConfig: FireframeRemoteConfig,
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        FireframeNavHost(
            appState = appState,
            remoteConfig = remoteConfig,
        )
    }
}
