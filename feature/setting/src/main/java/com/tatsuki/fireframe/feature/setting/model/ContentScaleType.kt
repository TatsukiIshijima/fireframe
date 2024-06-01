package com.tatsuki.fireframe.feature.setting.model

import com.tatsuki.fireframe.core.designsystem.model.SelectableItem
import com.tatsuki.fireframe.core.model.ContentScaleType.CROP
import com.tatsuki.fireframe.core.model.ContentScaleType.FILL_HEIGHT
import com.tatsuki.fireframe.core.model.ContentScaleType.FILL_WIDTH
import com.tatsuki.fireframe.core.model.ContentScaleType.FIT
import com.tatsuki.fireframe.core.model.ContentScaleType.INSIDE
import com.tatsuki.fireframe.feature.setting.R
import com.tatsuki.fireframe.core.model.ContentScaleType as ScaleType

sealed interface ContentScaleType : SelectableItem {

    data class Crop(
        override val nameResource: Int = R.string.crop,
    ) : ContentScaleType

    data class Fit(
        override val nameResource: Int = R.string.fit,
    ) : ContentScaleType

    data class FillHeight(
        override val nameResource: Int = R.string.fill_height,
    ) : ContentScaleType

    data class FillWidth(
        override val nameResource: Int = R.string.fill_width,
    ) : ContentScaleType

    data class Inside(
        override val nameResource: Int = R.string.inside,
    ) : ContentScaleType

    fun toDomain(): ScaleType {
        return when (this) {
            is Crop -> CROP
            is Fit -> FIT
            is FillHeight -> FILL_HEIGHT
            is FillWidth -> FILL_WIDTH
            is Inside -> INSIDE
        }
    }

    companion object {

        fun from(scaleType: ScaleType): ContentScaleType {
            return when (scaleType) {
                CROP -> Crop()
                FIT -> Fit()
                FILL_HEIGHT -> FillHeight()
                FILL_WIDTH -> FillWidth()
                INSIDE -> Inside()
            }
        }

        fun all(): List<ContentScaleType> {
            return ScaleType.entries
                .map { from(it) }
        }
    }
}
