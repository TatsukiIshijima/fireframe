package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.ContentScaleType
import com.tatsuki.fireframe.core.model.Location
import com.tatsuki.fireframe.core.model.SlideshowInterval
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    val locationFlow: Flow<Location>

    val selectedSlideGroupIdFlow: Flow<Long>

    val selectedSlideshowInterval: Flow<SlideshowInterval>

    val selectedContentScaleType: Flow<ContentScaleType>

    suspend fun updateLocation(location: Location)

    suspend fun updateSelectedSlideGroupId(groupId: Long)

    suspend fun updateSelectedSlideshowInterval(slideshowInterval: SlideshowInterval)

    suspend fun updateSelectedContentScaleType(contentScaleType: ContentScaleType)
}
