package com.tatsuki.fireframe.feature.mediaselector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.core.model.MediaImageDirectory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaSelectorViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val mutableImageDirectories = MutableStateFlow(emptyList<MediaImageDirectory>())
    val imageDirectories = mutableImageDirectories

    fun onGrantedReadExternalStoragePermission() {
        loadImageDirectories()
    }

    private fun loadImageDirectories() {
        viewModelScope.launch {
            try {
                val directories = mediaRepository.getAllImageDirectories()
                mutableImageDirectories.value = directories
            } catch (e: Exception) {
                Log.e("MediaPickerViewModel", "Failed to load directories", e)
                coroutineContext.ensureActive()
            }
        }
    }
}
