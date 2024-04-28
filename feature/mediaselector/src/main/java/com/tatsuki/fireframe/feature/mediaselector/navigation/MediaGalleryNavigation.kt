package com.tatsuki.fireframe.feature.mediaselector.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tatsuki.fireframe.feature.mediaselector.ui.MediaGalleryRoute

const val MEDIA_GALLERY_ROUTE = "media_gallery_route"

fun NavController.navigateToMediaGallery(navOptions: NavOptions? = null) {
    navigate(MEDIA_GALLERY_ROUTE, navOptions)
}

fun NavGraphBuilder.mediaGalleryScreen() {
    composable(route = MEDIA_GALLERY_ROUTE) {
        MediaGalleryRoute()
    }
}
