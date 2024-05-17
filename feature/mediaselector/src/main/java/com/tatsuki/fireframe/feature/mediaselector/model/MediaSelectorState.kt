package com.tatsuki.fireframe.feature.mediaselector.model

data class MediaSelectorState(
    val selectableLocalMediaDirectories: List<SelectableLocalMediaDirectory>,
    val shouldShowConfirmDialog: Boolean,
) {
    companion object {
        fun create(): MediaSelectorState {
            return MediaSelectorState(
                selectableLocalMediaDirectories = emptyList(),
                shouldShowConfirmDialog = false,
            )
        }

        fun fake(
            selectableLocalMediaDirectories: List<SelectableLocalMediaDirectory> =
                (0..10).map {
                    SelectableLocalMediaDirectory.fake(
                        name = "FakeDirectory$it",
                    )
                },
            shouldShowConfirmDialog: Boolean = false,
        ): MediaSelectorState {
            return MediaSelectorState(
                selectableLocalMediaDirectories = selectableLocalMediaDirectories,
                shouldShowConfirmDialog = shouldShowConfirmDialog,
            )
        }
    }
}
