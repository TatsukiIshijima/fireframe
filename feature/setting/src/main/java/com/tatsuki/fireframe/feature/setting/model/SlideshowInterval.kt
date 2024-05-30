package com.tatsuki.fireframe.feature.setting.model

import com.tatsuki.fireframe.core.designsystem.model.SelectableItem
import com.tatsuki.fireframe.core.model.SlideshowInterval.FIFTEEN_MINUTE
import com.tatsuki.fireframe.core.model.SlideshowInterval.FIVE_MINUTE
import com.tatsuki.fireframe.core.model.SlideshowInterval.ONE_HOUR
import com.tatsuki.fireframe.core.model.SlideshowInterval.ONE_MINUTE
import com.tatsuki.fireframe.core.model.SlideshowInterval.TEN_MINUTE
import com.tatsuki.fireframe.core.model.SlideshowInterval.THIRTY_MINUTE
import com.tatsuki.fireframe.core.model.SlideshowInterval as Interval

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

    fun toDomain(): Interval {
        return when (this) {
            is OneMinute -> ONE_MINUTE
            is FiveMinute -> FIVE_MINUTE
            is TenMinute -> TEN_MINUTE
            is FifteenMinute -> FIFTEEN_MINUTE
            is ThirtyMinute -> THIRTY_MINUTE
            is OneHour -> ONE_HOUR
        }
    }

    companion object {

        fun from(interval: Interval): SlideshowInterval {
            return when (interval) {
                ONE_MINUTE -> OneMinute()
                FIVE_MINUTE -> FiveMinute()
                TEN_MINUTE -> TenMinute()
                FIFTEEN_MINUTE -> FifteenMinute()
                THIRTY_MINUTE -> ThirtyMinute()
                ONE_HOUR -> OneHour()
            }
        }

        fun all(): List<SlideshowInterval> {
            return Interval.entries
                .map { from(it) }
        }
    }
}
