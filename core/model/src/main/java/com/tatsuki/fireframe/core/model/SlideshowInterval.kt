package com.tatsuki.fireframe.core.model

enum class SlideshowInterval(val value: Int) {
    ONE_MINUTE(1),
    FIVE_MINUTE(5),
    TEN_MINUTE(10),
    FIFTEEN_MINUTE(15),
    THIRTY_MINUTE(30),
    ONE_HOUR(60);

    companion object {
        fun from(value: Int): SlideshowInterval {
            val slideshowIntervalMap =
                SlideshowInterval.entries.associateBy(SlideshowInterval::value)
            return slideshowIntervalMap[value]
                ?.let { return it } ?: throw IllegalArgumentException("Unknown value: $value")
        }
    }
}