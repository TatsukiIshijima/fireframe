package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.database.MediaDataClient
import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDataClient: MediaDataClient,
) : MediaRepository {

    override suspend fun getAllImageDirectories(): List<MediaImageDirectory> {
        return mediaDataClient.queryAllImageDirectories()
    }
}
