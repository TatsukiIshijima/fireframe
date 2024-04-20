package com.tatsuki.fireframe.feature.mediaselector.model

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tatsuki.fireframe.core.common.toContentUri
import com.tatsuki.fireframe.core.model.MediaImage

data class SelectableMediaImage(
    val id: Long,
    val uri: Uri,
    val isSelected: MutableState<Boolean> = mutableStateOf(false),
) {
    companion object {
        fun from(mediaImage: MediaImage): SelectableMediaImage {
            return SelectableMediaImage(
                id = mediaImage.id,
                uri = mediaImage.id.toContentUri(),
                isSelected = mutableStateOf(false),
            )
        }

        fun fake(
            id: Long = 0,
            uri: Uri = Uri.EMPTY,
            isSelected: MutableState<Boolean> = mutableStateOf(false),
        ): SelectableMediaImage {
            return SelectableMediaImage(
                id = id,
                uri = uri,
                isSelected = isSelected,
            )
        }
    }
}
