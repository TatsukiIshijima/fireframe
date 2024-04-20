package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.database.MediaDataClient
import com.tatsuki.fireframe.core.database.dao.LocalMediaImageDao
import com.tatsuki.fireframe.core.database.model.asExternalModel
import com.tatsuki.fireframe.core.database.model.toEntity
import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDataClient: MediaDataClient,
    private val localMediaImageDao: LocalMediaImageDao,
) : MediaRepository {

    override suspend fun getAllImageDirectories(): List<MediaImageDirectory> {
        return mediaDataClient.queryAllImageDirectories()
    }

    override suspend fun getSelectedLocalMediaImages(): List<MediaImage> {
        return localMediaImageDao.getLocalMediaImages().map { entity ->
            entity.asExternalModel()
        }
    }

    override suspend fun updateSelectedLocalMediaImages(selectedMediaImages: List<MediaImage>) {
        localMediaImageDao.deleteLocalMediaImages()
        selectedMediaImages.forEach { mediaImage ->
            localMediaImageDao.insertLocalMediaImage(mediaImage.toEntity())
        }
    }
}
