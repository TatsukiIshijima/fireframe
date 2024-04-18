package com.tatsuki.fireframe.core.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.CancellationSignal
import android.provider.MediaStore
import android.util.Size

fun Context.toThumbnail(
    imageId: Long,
    // ThumbnailsConstants.MINI_KIND same size
    size: Size = Size(512, 384),
    signal: CancellationSignal? = null,
    options: BitmapFactory.Options? = null,
): Bitmap {
    return if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
        contentResolver.loadThumbnail(imageId.toContentUri(), size, signal)
    } else {
        MediaStore.Images.Thumbnails.getThumbnail(
            contentResolver,
            imageId,
            MediaStore.Images.Thumbnails.MINI_KIND,
            options,
        )
    }
}
