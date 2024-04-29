package com.tatsuki.fireframe.feature.mediaselector.model

import com.tatsuki.fireframe.core.model.LocalMediaDirectory

data class SelectableLocalMediaDirectory(
    val name: String,
    val selectableMediaImages: List<SelectableLocalMediaImage>,
) {
    companion object {
        fun from(directory: LocalMediaDirectory): SelectableLocalMediaDirectory {
            return SelectableLocalMediaDirectory(
                name = directory.name,
                selectableMediaImages = directory.images.map { image ->
                    SelectableLocalMediaImage.from(image)
                },
            )
        }

        fun fake(
            name: String = "FakeDirectory",
            selectableMediaImages: List<SelectableLocalMediaImage> = (0..10).map { SelectableLocalMediaImage.fake() },
        ): SelectableLocalMediaDirectory {
            return SelectableLocalMediaDirectory(
                name = name,
                selectableMediaImages = selectableMediaImages,
            )
        }
    }
}
