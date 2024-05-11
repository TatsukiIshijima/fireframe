package com.tatsuki.fireframe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
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

            FireframeTheme {
                FireframeApp(
                    appState = appState,
                    remoteConfig = fireframeRemoteConfig,
                )
            }
        }
    }
}
