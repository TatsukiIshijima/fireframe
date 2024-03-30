package com.tatsuki.fireframe.navigation

import com.tatsuki.fireframe.feature.slideshow.navigation.SLIDESHOW_ROUTE

sealed interface Destination {

    val routeName: String

    data class Slideshow(
        override val routeName: String = SLIDESHOW_ROUTE,
    ) : Destination
}
