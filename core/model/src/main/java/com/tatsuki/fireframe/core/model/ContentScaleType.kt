package com.tatsuki.fireframe.core.model

enum class ContentScaleType(val value: Int) {
    CROP(0),
    FIT(1),
    FILL_HEIGHT(2),
    FILL_WIDTH(3),
    INSIDE(4)
    ;

    companion object {
        fun from(value: Int): ContentScaleType {
            val contentScaleTypeMap = ContentScaleType.entries.associateBy(ContentScaleType::value)
            return contentScaleTypeMap[value]
                ?.let { return it } ?: throw IllegalArgumentException("Unknown value: $value")
        }
    }
}