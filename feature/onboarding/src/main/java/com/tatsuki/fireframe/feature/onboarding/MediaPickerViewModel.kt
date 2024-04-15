package com.tatsuki.fireframe.feature.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.common.network.toContentUri
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaPickerViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val mutableDirectories = MutableStateFlow(emptyList<MediaImageDirectory>())
    private val mutableImages = MutableStateFlow(emptyList<MediaImage>())
    val images = mutableImages.asStateFlow()

    fun onGrantedReadExternalStoragePermission() {
        loadImageDirectories()
        mutableDirectories.value.forEach { directory ->
            loadImageUrisFrom(directory.name)
        }
    }

    private fun loadImageDirectories() {
        viewModelScope.launch {
            try {
                val directories =mediaRepository.getAllImageDirectories()
                mutableDirectories.value = directories
            } catch (e: Exception) {
                Log.e("MediaPickerViewModel", "Failed to load directories", e)
                coroutineContext.ensureActive()
            }
        }
    }

    private fun loadImageUrisFrom(directory: String) {
        viewModelScope.launch {
            try {
               val images = mediaRepository.getImagesFromDirectory(directory)
                mutableImages.value = images
            } catch (e: Exception) {
                Log.e("MediaPickerViewModel", "Failed to load image urls", e)
                coroutineContext.ensureActive()
            }
        }
    }
}
