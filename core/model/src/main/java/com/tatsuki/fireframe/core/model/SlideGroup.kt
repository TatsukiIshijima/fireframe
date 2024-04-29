package com.tatsuki.fireframe.core.model

data class SlideGroup(
    val id: Long,
    val groupName: String,
) {
    companion object {
        fun fake(
            id: Long = 0,
            groupName: String = "FakeGroup",
        ) = SlideGroup(
            id = id,
            groupName = groupName,
        )
    }
}
