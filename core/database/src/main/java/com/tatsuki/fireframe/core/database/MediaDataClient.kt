package com.tatsuki.fireframe.core.database

import com.tatsuki.fireframe.core.database.model.ImageDirectoryEntity
import com.tatsuki.fireframe.core.database.model.ImageEntity

interface MediaDataClient {

    fun queryAllImageDirectories(): List<ImageDirectoryEntity>

    fun queryImagesFromDirectory(name: String): List<ImageEntity>
}