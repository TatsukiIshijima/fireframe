package com.tatsuki.fireframe

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tatsuki.fireframe.core.common.setVisibleSystemBars
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import com.tatsuki.fireframe.feature.home.navigation.HOME_ROUTE
import com.tatsuki.fireframe.feature.mediaselector.navigation.MEDIA_SELECTOR_ROUTE
import com.tatsuki.fireframe.feature.slideshow.navigation.SLIDESHOW_ROUTE
import com.tatsuki.fireframe.ui.FireframeApp
import com.tatsuki.fireframe.ui.rememberFireframeAppState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var fireframeRemoteConfig: FireframeRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appState = rememberFireframeAppState()
            when (appState.currentDestination?.route) {
                HOME_ROUTE,
                MEDIA_SELECTOR_ROUTE,
                null,
                -> {
                    window.setVisibleSystemBars(true)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }

                SLIDESHOW_ROUTE -> {
                    window.setVisibleSystemBars(false)
                    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            }

            FireframeTheme {
                FireframeApp(
                    appState = appState,
                    remoteConfig = fireframeRemoteConfig,
                )
            }
        }
    }
}
