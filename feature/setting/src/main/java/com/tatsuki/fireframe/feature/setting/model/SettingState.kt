package com.tatsuki.fireframe.feature.setting.model

data class SettingState(
    val slideshowInterval: SlideshowInterval,
    val contentScaleType: ContentScaleType,
    val shouldShowSlideshowIntervalSettingDialog: Boolean,
    val shouldShowContentScaleTypeSettingDialog: Boolean,
) {
    companion object {
        fun create(): SettingState {
            return SettingState(
                slideshowInterval = SlideshowInterval.OneMinute(),
                contentScaleType = ContentScaleType.Crop(),
                shouldShowSlideshowIntervalSettingDialog = false,
                shouldShowContentScaleTypeSettingDialog = false,
            )
        }
    }
}
