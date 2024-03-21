package com.tatsuki.fireframe.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
data class FireframeAppState(
    private val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope,
) {
    val drawer: DrawerState
        get() = this.drawerState

    fun openDrawer() {
        this.coroutineScope.launch {
            drawerState.open()
        }
    }

    fun closeDrawer() {
        this.coroutineScope.launch {
            drawerState.close()
        }
    }
}

@Composable
fun rememberFireframeAppState(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): FireframeAppState {
    return FireframeAppState(
        drawerState = drawerState,
        coroutineScope = coroutineScope,
    )
}
