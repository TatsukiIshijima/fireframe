package com.tatsuki.fireframe.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tatsuki.fireframe.Greeting

@Composable
fun FireframeApp(appState: FireframeAppState) {
    ModalNavigationDrawer(
        drawerState = appState.drawer,
        drawerContent = {
            FireframeAppMenuDrawer(
                onClickMenuItem = { _ ->
                    appState.closeDrawer()
                },
            )
        },
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Greeting("Android")
        }
    }
}