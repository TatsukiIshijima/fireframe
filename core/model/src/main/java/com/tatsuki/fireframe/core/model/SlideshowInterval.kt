package com.tatsuki.fireframe.core.model

enum class SlideshowInterval(val value: Long) {
    // value's unit is milliseconds.
    ONE_MINUTE(60000L),
    FIVE_MINUTE(300000L),
    TEN_MINUTE(600000L),
    FIFTEEN_MINUTE(900000L),
    THIRTY_MINUTE(1800000L),
    ONE_HOUR(3600000L),
    ;

    companion object {
        fun from(value: Long): SlideshowInterval {
            val slideshowIntervalMap =
                SlideshowInterval.entries.associateBy(SlideshowInterval::value)
            return slideshowIntervalMap[value]
                ?.let { return it } ?: throw IllegalArgumentException("Unknown value: $value")
        }
    }
}
