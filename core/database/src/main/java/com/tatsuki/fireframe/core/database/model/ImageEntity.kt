package com.tatsuki.fireframe.core.database.model

import android.net.Uri

data class ImageEntity(
    val id: Long,
    val path: String,
    val uri: Uri,
    val name: String,
)
