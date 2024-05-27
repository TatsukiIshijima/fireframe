package com.tatsuki.fireframe.feature.setting.model

import com.tatsuki.fireframe.core.designsystem.model.SelectableItem

sealed interface ContentScaleType : SelectableItem {

    data class Crop(
        override val name: String = "クロップ",
    ) : ContentScaleType

    data class Fit(
        override val name: String = "フィット",
    ) : ContentScaleType

    data class FillHeight(
        override val name: String = "フィル（高さ）",
    ) : ContentScaleType

    data class FillWidth(
        override val name: String = "フィル（幅）",
    ) : ContentScaleType

    data class Inside(
        override val name: String = "インサイド",
    ) : ContentScaleType

}