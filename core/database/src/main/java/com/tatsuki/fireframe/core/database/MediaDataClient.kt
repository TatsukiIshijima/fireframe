package com.tatsuki.fireframe.core.database

import com.tatsuki.fireframe.core.model.MediaImageDirectory

interface MediaDataClient {

    suspend fun queryAllImageDirectories(): List<MediaImageDirectory>

//    suspend fun queryImagesFromDirectory(name: String): List<MediaImage>
}
