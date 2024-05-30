package com.tatsuki.fireframe.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.SettingRepository
import com.tatsuki.fireframe.feature.setting.model.ContentScaleType
import com.tatsuki.fireframe.feature.setting.model.SettingState
import com.tatsuki.fireframe.feature.setting.model.SlideshowInterval
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    private val selectedSlideshowInterval = settingRepository.selectedSlideshowInterval
    private val selectedContentScaleType = settingRepository.selectedContentScaleType
    private val mutableShouldShowSlideshowIntervalSettingDialog = MutableStateFlow(false)
    private val mutableShouldShowContentScaleTypeSettingDialog = MutableStateFlow(false)

    val settingState = combine(
        selectedSlideshowInterval,
        selectedContentScaleType,
        mutableShouldShowSlideshowIntervalSettingDialog,
        mutableShouldShowContentScaleTypeSettingDialog,
    ) { slideshowInterval, contentScaleType, shouldShowSlideshowIntervalSettingDialog, shouldShowContentScaleTypeSettingDialog ->
        SettingState(
            slideshowInterval = SlideshowInterval.from(slideshowInterval),
            contentScaleType = ContentScaleType.from(contentScaleType),
            shouldShowSlideshowIntervalSettingDialog = shouldShowSlideshowIntervalSettingDialog,
            shouldShowContentScaleTypeSettingDialog = shouldShowContentScaleTypeSettingDialog,
        )
    }

    fun onUpdateSlideshowInterval(slideshowInterval: SlideshowInterval) {
        viewModelScope.launch {
            settingRepository.updateSelectedSlideshowInterval(slideshowInterval.toDomain())
        }
    }

    fun onUpdateContentScaleType(contentScaleType: ContentScaleType) {
        viewModelScope.launch {
            settingRepository.updateSelectedContentScaleType(contentScaleType.toDomain())
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
