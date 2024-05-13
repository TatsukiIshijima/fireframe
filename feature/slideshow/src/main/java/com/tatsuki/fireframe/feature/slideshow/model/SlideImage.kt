package com.tatsuki.fireframe.feature.slideshow.model

import android.net.Uri
import com.tatsuki.fireframe.core.common.toContentUri
import com.tatsuki.fireframe.core.model.LocalMediaImage

data class SlideImage(
    val id: Long,
    val uri: Uri,
) {
    companion object {
        fun from(localMediaImage: LocalMediaImage): SlideImage {
            return SlideImage(
                id = localMediaImage.id,
                uri = localMediaImage.id.toContentUri(),
            )
        }

        fun fake(
            id: Long = 0,
            uri: Uri = Uri.EMPTY,
        ): SlideImage {
            return SlideImage(
                id = id,
                uri = uri,
            )
        }
    }
}
