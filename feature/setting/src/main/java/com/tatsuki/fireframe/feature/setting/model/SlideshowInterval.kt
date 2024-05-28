package com.tatsuki.fireframe.feature.setting.model

import com.tatsuki.fireframe.core.designsystem.model.SelectableItem

sealed interface SlideshowInterval : SelectableItem {

    data class OneMinute(
        override val name: String = "1分",
    ) : SlideshowInterval

    data class FiveMinute(
        override val name: String = "5分",
    ) : SlideshowInterval

    data class TenMinute(
        override val name: String = "10分",
    ) : SlideshowInterval

    data class FifteenMinute(
        override val name: String = "15分",
    ) : SlideshowInterval

    data class ThirtyMinute(
        override val name: String = "30分",
    ) : SlideshowInterval

    data class OneHour(
        override val name: String = "1時間",
    ) : SlideshowInterval

    companion object {
        fun all(): List<SlideshowInterval> {
            return listOf(
                OneMinute(),
                FiveMinute(),
                TenMinute(),
                FifteenMinute(),
                ThirtyMinute(),
                OneHour(),
            )
        }
    }
}
