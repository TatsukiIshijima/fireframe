package com.tatsuki.fireframe.core.designsystem.model

sealed interface SelectableItem {
    val name: String
}

data class FakeSelectableItem(
    override val name: String,
) : SelectableItem