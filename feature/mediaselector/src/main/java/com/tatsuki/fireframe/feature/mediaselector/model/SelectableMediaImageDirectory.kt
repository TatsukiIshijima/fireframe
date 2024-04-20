package com.tatsuki.fireframe.feature.mediaselector.model

import com.tatsuki.fireframe.core.model.MediaImageDirectory

data class SelectableMediaImageDirectory(
    val name: String,
    val selectableMediaImages: List<SelectableMediaImage>,
) {
    companion object {
        fun from(directory: MediaImageDirectory): SelectableMediaImageDirectory {
            return SelectableMediaImageDirectory(
                name = directory.name,
                selectableMediaImages = directory.images.map { image ->
                    SelectableMediaImage.from(image)
                },
            )
        }

        fun fake(
            name: String = "FakeDirectory",
            selectableMediaImages: List<SelectableMediaImage> = (0..10).map { SelectableMediaImage.fake() },
        ): SelectableMediaImageDirectory {
            return SelectableMediaImageDirectory(
                name = name,
                selectableMediaImages = selectableMediaImages,
            )
        }
    }
}
