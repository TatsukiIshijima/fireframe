package com.tatsuki.fireframe.navigation

import com.tatsuki.fireframe.feature.home.navigation.HOME_ROUTE
import com.tatsuki.fireframe.feature.mediaselector.navigation.MEDIA_SELECTOR_ROUTE
import com.tatsuki.fireframe.feature.setting.navigation.SETTING_ROUTE
import com.tatsuki.fireframe.feature.slideshow.navigation.SLIDESHOW_ROUTE

sealed interface Destination {

    val routeName: String

    data class Home(
        override val routeName: String = HOME_ROUTE,
    ) : Destination

    data class Slideshow(
        override val routeName: String = SLIDESHOW_ROUTE,
    ) : Destination

    data class MediaSelector(
        override val routeName: String = MEDIA_SELECTOR_ROUTE,
    ) : Destination

    data class Setting(
        override val routeName: String = SETTING_ROUTE,
    ) : Destination
}
