package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory

interface MediaRepository {

    suspend fun getAllImageDirectories(): List<MediaImageDirectory>

    suspend fun getImagesFromDirectory(name: String): List<MediaImage>
}