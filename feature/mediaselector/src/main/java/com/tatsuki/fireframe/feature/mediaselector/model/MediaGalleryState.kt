package com.tatsuki.fireframe.feature.mediaselector.model

data class MediaGalleryState(
    val groupName: String,
    val images: List<SelectableLocalMediaImage>,
) {
    companion object {
        fun empty(): MediaGalleryState {
            return MediaGalleryState(
                groupName = "",
                images = emptyList(),
            )
        }
    }
}
