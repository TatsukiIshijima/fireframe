package com.tatsuki.fireframe.feature.setting

import androidx.lifecycle.ViewModel
import com.tatsuki.fireframe.core.data.repository.SettingRepository
import com.tatsuki.fireframe.feature.setting.model.ContentScaleType
import com.tatsuki.fireframe.feature.setting.model.ContentScaleType.Crop
import com.tatsuki.fireframe.feature.setting.model.SettingState
import com.tatsuki.fireframe.feature.setting.model.SlideshowInterval
import com.tatsuki.fireframe.feature.setting.model.SlideshowInterval.OneMinute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    private val mutableSlideshowInterval = MutableStateFlow<SlideshowInterval>(OneMinute())
    private val mutableContentScaleType = MutableStateFlow<ContentScaleType>(Crop())
    private val mutableShouldShowSlideshowIntervalSettingDialog = MutableStateFlow(false)
    private val mutableShouldShowContentScaleTypeSettingDialog = MutableStateFlow(false)

    val settingState = combine(
        mutableSlideshowInterval,
        mutableContentScaleType,
        mutableShouldShowSlideshowIntervalSettingDialog,
        mutableShouldShowContentScaleTypeSettingDialog,
    ) { slideshowInterval, contentScaleType, shouldShowSlideshowIntervalSettingDialog, shouldShowContentScaleTypeSettingDialog ->
        SettingState(
            slideshowInterval = slideshowInterval,
            contentScaleType = contentScaleType,
            shouldShowSlideshowIntervalSettingDialog = shouldShowSlideshowIntervalSettingDialog,
            shouldShowContentScaleTypeSettingDialog = shouldShowContentScaleTypeSettingDialog,
        )
    }

    fun loadSettings() {
        // TODO:Implements
    }

    fun onShowSlideshowIntervalSettingDialog() {
        mutableShouldShowSlideshowIntervalSettingDialog.value = true
    }

    fun onDismissSlideshowIntervalSettingDialog() {
        mutableShouldShowSlideshowIntervalSettingDialog.value = false
    }

    fun onShowContentScaleTypeSettingDialog() {
        mutableShouldShowContentScaleTypeSettingDialog.value = true
    }

    fun onDismissContentScaleTypeSettingDialog() {
        mutableShouldShowContentScaleTypeSettingDialog.value = false
    }
}
