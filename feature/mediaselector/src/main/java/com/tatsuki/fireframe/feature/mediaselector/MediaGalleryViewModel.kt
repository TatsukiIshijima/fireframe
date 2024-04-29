package com.tatsuki.fireframe.feature.mediaselector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.feature.mediaselector.model.MediaGalleryState
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaGalleryViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val mutableMediaGalleryState = MutableStateFlow(MediaGalleryState.empty())
    val mediaGalleryState = mutableMediaGalleryState.asStateFlow()

    fun loadSlideGroup(slideGroupId: Long) {
        viewModelScope.launch {
            try {
                val slideGroupName = mediaRepository.getSlideGroup(slideGroupId).groupName
                val slideGroupImages = mediaRepository.getSlideGroupImages(slideGroupId)
                    .map { image -> SelectableLocalMediaImage.from(image) }
                mutableMediaGalleryState.value = MediaGalleryState(
                    groupName = slideGroupName,
                    images = slideGroupImages,
                )
            } catch (e: Exception) {
                Log.e("MediaGalleryViewModel", "Failed to load selected images", e)
                coroutineContext.ensureActive()
            }
        }
    }
}
