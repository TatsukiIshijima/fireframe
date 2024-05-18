package com.tatsuki.fireframe.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.core.data.repository.SettingRepository
import com.tatsuki.fireframe.core.model.SlideGroup
import com.tatsuki.fireframe.feature.home.model.HomeState
import com.tatsuki.fireframe.feature.home.model.SourceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val settingRepository: SettingRepository,
) : ViewModel() {

    private val mutableSlideGroups = MutableStateFlow(emptyList<SlideGroup>())
    private val selectedSlideGroupId = settingRepository.selectedSlideGroupIdFlow
    private val mutableDeleteTargetSlideGroup = MutableStateFlow<SlideGroup?>(null)

    val homeState = combine(
        mutableSlideGroups,
        selectedSlideGroupId,
        mutableDeleteTargetSlideGroup,
    ) { slideGroups, selectedSlideGroupId, deleteTargetSlideGroup ->
        HomeState(
            sourceTypes = listOf(SourceType.LocalStorage()),
            slideshowGroups = slideGroups,
            selectedSlideGroupId = selectedSlideGroupId,
            deleteTargetSlideGroup = deleteTargetSlideGroup,
            isEnableSourceTypes = slideGroups.count() < MAX_SLIDE_GROUP_COUNT,
        )
    }

    fun onCreate() {
        loadSlideGroups()
    }

    private fun loadSlideGroups() {
        viewModelScope.launch {
            try {
                mutableSlideGroups.value = mediaRepository.getSlideGroups()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Failed to load slide groups", e)
                coroutineContext.ensureActive()
            }
        }
    }

    fun onSelectSlideGroup(slideGroup: SlideGroup) {
        viewModelScope.launch {
            settingRepository.updateSelectedSlideGroupId(slideGroup.id)
        }
    }

    fun onClickDeleteSlideGroupButton(slideGroup: SlideGroup) {
        mutableDeleteTargetSlideGroup.value = slideGroup
    }

    fun onDeleteSlideGroupCancel() {
        mutableDeleteTargetSlideGroup.value = null
    }

    fun onDeleteSlideGroup() {
        viewModelScope.launch {
            try {
                val deleteTargetSlideGroup = mutableDeleteTargetSlideGroup.value
                    ?: throw IllegalStateException("Delete target slide group is null")
                mediaRepository.deleteSlideGroup(deleteTargetSlideGroup.id)
                if (selectedSlideGroupId.first() == deleteTargetSlideGroup.id) {
                    settingRepository.updateSelectedSlideGroupId(-1L)
                }
                mutableDeleteTargetSlideGroup.value = null
                mutableSlideGroups.value = mediaRepository.getSlideGroups()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Failed to delete slide group", e)
                coroutineContext.ensureActive()
            }
        }
    }
}

private const val MAX_SLIDE_GROUP_COUNT = 2
