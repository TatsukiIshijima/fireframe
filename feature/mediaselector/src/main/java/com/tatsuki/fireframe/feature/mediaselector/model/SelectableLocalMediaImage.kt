package com.tatsuki.fireframe.feature.mediaselector.model

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tatsuki.fireframe.core.common.toContentUri
import com.tatsuki.fireframe.core.model.LocalMediaImage

data class SelectableLocalMediaImage(
    val id: Long,
    val uri: Uri,
    val isSelected: MutableState<Boolean> = mutableStateOf(false),
) {

    fun toLocalImage(): LocalMediaImage {
        return LocalMediaImage(
            id = id,
        )
    }

    companion object {
        fun from(localMediaImage: LocalMediaImage): SelectableLocalMediaImage {
            return SelectableLocalMediaImage(
                id = localMediaImage.id,
                uri = localMediaImage.id.toContentUri(),
                isSelected = mutableStateOf(false),
            )
        }

        fun fake(
            id: Long = 0,
            uri: Uri = Uri.EMPTY,
            isSelected: MutableState<Boolean> = mutableStateOf(false),
        ): SelectableLocalMediaImage {
            return SelectableLocalMediaImage(
                id = id,
                uri = uri,
                isSelected = isSelected,
            )
        }
    }
}
