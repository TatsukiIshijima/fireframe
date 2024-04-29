package com.tatsuki.fireframe.feature.mediaselector.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tatsuki.fireframe.core.model.SlideGroup
import com.tatsuki.fireframe.feature.mediaselector.ui.MediaGalleryRoute

private const val MEDIA_GALLERY_ROUTE = "mediaGallery"
private const val MEDIA_GALLERY_SLIDE_GROUP_ID_PATH = "slideGroupId"

fun NavController.navigateToMediaGallery(
    slideGroup: SlideGroup,
    navOptions: NavOptions? = null,
) {
    val path = MEDIA_GALLERY_ROUTE + "/${slideGroup.id}"
    navigate(path, navOptions)
}

fun NavGraphBuilder.mediaGalleryScreen() {
    composable(
        route = "$MEDIA_GALLERY_ROUTE/{$MEDIA_GALLERY_SLIDE_GROUP_ID_PATH}",
        arguments = listOf(navArgument(MEDIA_GALLERY_SLIDE_GROUP_ID_PATH) { type = NavType.LongType }),
    ) { backStackEntry ->
        backStackEntry.arguments?.getLong(MEDIA_GALLERY_SLIDE_GROUP_ID_PATH)?.let { slideGroupId ->
            MediaGalleryRoute(
                slideGroupId = slideGroupId,
            )
        }
    }
}
