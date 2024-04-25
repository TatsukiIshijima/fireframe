package com.tatsuki.fireframe.feature.mediaselector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableMediaImage
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

    private val mutableSelectedImages =
        MutableStateFlow(emptyList<SelectableMediaImage>())
    val selectedImages = mutableSelectedImages.asStateFlow()

    fun loadSelectedImages() {
        viewModelScope.launch {
            try {
                val selectedImages = mediaRepository.getSelectedLocalMediaImages()
                mutableSelectedImages.value = selectedImages.map { image ->
                    SelectableMediaImage.from(image)
                }
            } catch (e: Exception) {
                Log.e("MediaGalleryViewModel", "Failed to load selected images", e)
                coroutineContext.ensureActive()
            }
        }
    }
}
