package com.tatsuki.fireframe.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
data class FireframeAppState(
    val navController: NavHostController,
    private val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

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
    navController: NavHostController = rememberNavController(),
): FireframeAppState {
    return FireframeAppState(
        navController = navController,
        drawerState = drawerState,
        coroutineScope = coroutineScope,
    )
}
