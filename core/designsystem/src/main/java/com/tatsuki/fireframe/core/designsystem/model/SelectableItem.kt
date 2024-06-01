package com.tatsuki.fireframe.core.designsystem.model

interface SelectableItem {
    val nameResource: Int
}

data class FakeSelectableItem(
    override val nameResource: Int,
) : SelectableItem
