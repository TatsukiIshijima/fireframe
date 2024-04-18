package com.tatsuki.fireframe.core.common

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore

fun Long.toContentUri(): Uri =
    ContentUris.withAppendedId(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        this,
    )
