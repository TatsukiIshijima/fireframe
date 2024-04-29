package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.database.MediaDataClient
import com.tatsuki.fireframe.core.database.dao.LocalMediaImageDao
import com.tatsuki.fireframe.core.database.dao.SlideGroupDao
import com.tatsuki.fireframe.core.database.model.LocalMediaImageEntity
import com.tatsuki.fireframe.core.database.model.SlideGroupEntity
import com.tatsuki.fireframe.core.database.model.asExternalModel
import com.tatsuki.fireframe.core.model.LocalMediaImage
import com.tatsuki.fireframe.core.model.LocalMediaDirectory
import com.tatsuki.fireframe.core.model.SlideGroup
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDataClient: MediaDataClient,
    private val localMediaImageDao: LocalMediaImageDao,
    private val slideGroupDao: SlideGroupDao,
) : MediaRepository {

    override suspend fun getSlideGroups(): List<SlideGroup> {
        return slideGroupDao.getSlideGroups().map { entity ->
            entity.asExternalModel()
        }
    }

    override suspend fun getSlideGroupImages(groupId: Long): List<LocalMediaImage> {
        return localMediaImageDao.getLocalMediaImagesByGroupId(groupId).map { entity ->
            entity.asExternalModel()
        }
    }

    override suspend fun getAllLocalMediaDirectories(): List<LocalMediaDirectory> {
        return mediaDataClient.queryAllLocalMediaDirectories()
    }

    override suspend fun createSlideGroup(
        slideGroupName: String,
        localImages: List<LocalMediaImage>,
    ) {
        val groupIds = slideGroupDao.insertSlideGroup(
            SlideGroupEntity(
                groupName = slideGroupName,
            ),
        )
        localMediaImageDao.insertLocalMediaImages(
            localImages.map { image ->
                LocalMediaImageEntity.from(
                    localMediaImage = image,
                    groupId = groupIds.first(),
                )
            },
        )
    }

    override suspend fun deleteSlideGroup(groupId: Long) {
        localMediaImageDao.deleteLocalMediaImagesByGroupId(groupId)
        slideGroupDao.deleteSlideGroup(groupId)
    }
}
