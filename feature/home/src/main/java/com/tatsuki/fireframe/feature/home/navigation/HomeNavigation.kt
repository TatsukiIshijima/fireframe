package com.tatsuki.fireframe.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tatsuki.fireframe.feature.home.model.SourceType
import com.tatsuki.fireframe.feature.home.ui.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onClickSource: (SourceType) -> Unit,
    onOpenSlideGroup: (String) -> Unit,
    onClickSlideStart: () -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onClickSource = onClickSource,
            onOpenSlideGroup = onOpenSlideGroup,
            onClickSlideStart = onClickSlideStart
        )
    }
}
