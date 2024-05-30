package com.tatsuki.fireframe.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.SettingRepository
import com.tatsuki.fireframe.feature.setting.model.ContentScaleType
import com.tatsuki.fireframe.feature.setting.model.ContentScaleType.Crop
import com.tatsuki.fireframe.feature.setting.model.SettingState
import com.tatsuki.fireframe.feature.setting.model.SlideshowInterval
import com.tatsuki.fireframe.feature.setting.model.SlideshowInterval.OneMinute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    private val mutableSelectSlideshowInterval = MutableStateFlow<SlideshowInterval>(OneMinute())
    private val mutableSlideshowIntervalSetting = MutableStateFlow<SlideshowInterval>(OneMinute())
    private val mutableContentScaleTypeSetting = MutableStateFlow<ContentScaleType>(Crop())
    private val mutableShouldShowSlideshowIntervalSettingDialog = MutableStateFlow(false)
    private val mutableShouldShowContentScaleTypeSettingDialog = MutableStateFlow(false)

    val settingState = combine(
        mutableSlideshowIntervalSetting,
        mutableContentScaleTypeSetting,
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

    fun onCreate() {
        viewModelScope.launch {
            loadSettings()
        }
    }

    private suspend fun loadSettings() {
        mutableSlideshowIntervalSetting.value =
            SlideshowInterval.from(settingRepository.selectedSlideshowInterval.first())
        mutableContentScaleTypeSetting.value =
            ContentScaleType.from(settingRepository.selectedContentScaleType.first())
    }

    fun onSelectSlideshowInterval(slideshowInterval: SlideshowInterval) {
        mutableSlideshowIntervalSetting.value = slideshowInterval
    }

    fun onUpdateSlideshowInterval() {
        viewModelScope.launch {
            settingRepository.updateSelectedSlideshowInterval(mutableSlideshowIntervalSetting.value.toDomain())
        }
    }

    fun onSelectContentScaleType(contentScaleType: ContentScaleType) {
        mutableContentScaleTypeSetting.value = contentScaleType
    }

    fun onUpdateContentScaleType() {
        viewModelScope.launch {
            settingRepository.updateSelectedContentScaleType(mutableContentScaleTypeSetting.value.toDomain())
        }
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
