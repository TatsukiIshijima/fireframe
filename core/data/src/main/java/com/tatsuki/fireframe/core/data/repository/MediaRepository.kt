package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory

interface MediaRepository {

    suspend fun getAllImageDirectories(): List<MediaImageDirectory>

    suspend fun getSelectedLocalMediaImages(): List<MediaImage>

    suspend fun updateSelectedLocalMediaImages(selectedMediaImages: List<MediaImage>)
}
