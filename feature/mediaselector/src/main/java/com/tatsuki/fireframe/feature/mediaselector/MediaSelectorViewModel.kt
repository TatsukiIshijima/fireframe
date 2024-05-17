package com.tatsuki.fireframe.feature.mediaselector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.feature.mediaselector.model.MediaSelectorState
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaDirectory
import com.tatsuki.fireframe.feature.mediaselector.model.SelectableLocalMediaImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaSelectorViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val mutableImageDirectories =
        MutableStateFlow(emptyList<SelectableLocalMediaDirectory>())
    private val mutableShouldShowConfirmDialog = MutableStateFlow(false)

    val mediaSelectorState = combine(
        mutableImageDirectories,
        mutableShouldShowConfirmDialog,
    ) { imageDirectories, shouldShowConfirmDialog ->
        MediaSelectorState(
            selectableLocalMediaDirectories = imageDirectories,
            shouldShowConfirmDialog = shouldShowConfirmDialog,
        )
    }

    fun onGrantedReadExternalStoragePermission() {
        loadLocalMediaDirectories()
    }

    private fun loadLocalMediaDirectories() {
        viewModelScope.launch {
            try {
                val directories = mediaRepository.getAllLocalMediaDirectories()
                    .map { directory -> SelectableLocalMediaDirectory.from(directory) }
                mutableImageDirectories.value = directories
            } catch (e: Exception) {
                Log.e("MediaSelectorViewModel", "Failed to load directories", e)
                coroutineContext.ensureActive()
            }
        }
    }

    fun onSelect(selectableMediaImage: SelectableLocalMediaImage) {
        mutableImageDirectories.value
            .flatMap { directory -> directory.selectableMediaImages }
            .find { image -> image.id == selectableMediaImage.id }
            ?.let { image ->
                image.isSelected.value = !image.isSelected.value
                Log.d("MediaSelectorViewModel", "onSelect: $image")
            }
    }

    fun onShowConfirmDialog() {
        mutableShouldShowConfirmDialog.value = true
    }

    fun onDismissConfirmDialog() {
        mutableShouldShowConfirmDialog.value = false
    }

    fun onSaveSlideGroup(slideGroupName: String) {
        viewModelScope.launch {
            try {
                val selectedLocalImages = mutableImageDirectories.value
                    .flatMap { directory -> directory.selectableMediaImages }
                    .filter { image -> image.isSelected.value }
                    .map { image -> image.toLocalImage() }
                mediaRepository.createSlideGroup(
                    slideGroupName = slideGroupName,
                    localImages = selectedLocalImages,
                )
                mutableShouldShowConfirmDialog.value = false
            } catch (e: Exception) {
                Log.e("MediaSelectorViewModel", "Failed to save slide group", e)
                coroutineContext.ensureActive()
            }
        }
    }
}
