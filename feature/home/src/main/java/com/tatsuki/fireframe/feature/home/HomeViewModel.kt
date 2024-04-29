package com.tatsuki.fireframe.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.core.model.SlideGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val mutableSlideGroups = MutableStateFlow(emptyList<SlideGroup>())
    val slideGroups = mutableSlideGroups.asStateFlow()

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

    fun onDeleteSlideGroup(slideGroup: SlideGroup) {
        viewModelScope.launch {
            try {
                mediaRepository.deleteSlideGroup(slideGroup.id)
                mutableSlideGroups.value = mediaRepository.getSlideGroups()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Failed to delete slide group", e)
                coroutineContext.ensureActive()
            }
        }
    }
}
