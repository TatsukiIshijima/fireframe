package com.tatsuki.fireframe.navigation

import com.tatsuki.fireframe.feature.mediaselector.navigation.MEDIA_SELECTOR_ROUTE
import com.tatsuki.fireframe.feature.slideshow.navigation.SLIDESHOW_ROUTE

sealed interface Destination {

    val routeName: String

    data class Slideshow(
        override val routeName: String = SLIDESHOW_ROUTE,
    ) : Destination

    data class MediaSelector(
        override val routeName: String = MEDIA_SELECTOR_ROUTE,
    ) : Destination
}
