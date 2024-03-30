package com.tatsuki.fireframe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tatsuki.fireframe.ui.FireframeApp
import com.tatsuki.fireframe.ui.rememberFireframeAppState
import com.tatsuki.fireframe.ui.theme.FireframeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberFireframeAppState()

            FireframeTheme {
                FireframeApp(appState)
            }
        }
    }
}
