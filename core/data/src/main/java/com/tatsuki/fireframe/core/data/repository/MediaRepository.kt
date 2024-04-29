package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.LocalMediaImage
import com.tatsuki.fireframe.core.model.LocalMediaDirectory
import com.tatsuki.fireframe.core.model.SlideGroup

interface MediaRepository {

    suspend fun getSlideGroups(): List<SlideGroup>

    suspend fun getSlideGroupImages(groupId: Long): List<LocalMediaImage>

    suspend fun getAllLocalMediaDirectories(): List<LocalMediaDirectory>

    suspend fun createSlideGroup(
        slideGroupName: String,
        localImages: List<LocalMediaImage>,
    )

    suspend fun deleteSlideGroup(groupId: Long)
}
