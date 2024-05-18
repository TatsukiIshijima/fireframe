package com.tatsuki.fireframe.feature.mediaselector.model

data class MediaSelectorState(
    val selectableLocalMediaDirectories: List<SelectableLocalMediaDirectory>,
    val isEnableSaveButton: Boolean,
    val shouldShowConfirmDialog: Boolean,
) {
    companion object {
        fun create(): MediaSelectorState {
            return MediaSelectorState(
                selectableLocalMediaDirectories = emptyList(),
                isEnableSaveButton = false,
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
            isEnableSaveButton: Boolean = false,
            shouldShowConfirmDialog: Boolean = false,
        ): MediaSelectorState {
            return MediaSelectorState(
                selectableLocalMediaDirectories = selectableLocalMediaDirectories,
                isEnableSaveButton = isEnableSaveButton,
                shouldShowConfirmDialog = shouldShowConfirmDialog,
            )
        }
    }
}
