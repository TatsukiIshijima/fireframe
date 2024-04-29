package com.tatsuki.fireframe.core.model

data class LocalMediaDirectory(
    val id: Long,
    val name: String,
    val images: List<LocalMediaImage>,
)
