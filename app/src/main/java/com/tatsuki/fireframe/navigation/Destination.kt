package com.tatsuki.fireframe.navigation

import com.tatsuki.fireframe.feature.onboarding.navigation.MEDIA_PICKER_ROUTE
import com.tatsuki.fireframe.feature.slideshow.navigation.SLIDESHOW_ROUTE

sealed interface Destination {

    val routeName: String

    data class Slideshow(
        override val routeName: String = SLIDESHOW_ROUTE,
    ) : Destination

    data class MediaPicker(
        override val routeName: String = MEDIA_PICKER_ROUTE,
    ) : Destination
}
