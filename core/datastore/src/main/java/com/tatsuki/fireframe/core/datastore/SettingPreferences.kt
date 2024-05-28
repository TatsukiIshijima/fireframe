package com.tatsuki.fireframe.core.datastore

import com.tatsuki.fireframe.core.model.ContentScaleType
import com.tatsuki.fireframe.core.model.Location
import com.tatsuki.fireframe.core.model.SlideshowInterval
import kotlinx.coroutines.flow.Flow

interface SettingPreferences {

    val locationFlow: Flow<Location>

    val selectedSlideGroupIdFlow: Flow<Long>

    val selectedSlideshowInterval: Flow<Int>

    val selectedContentScaleType: Flow<Int>

    suspend fun updateLocation(location: Location)

    suspend fun updateSelectedSlideGroupId(groupId: Long)

    suspend fun updateSelectedSlideshowInterval(slideshowInterval: SlideshowInterval)

    suspend fun updateSelectedContentScaleType(contentScaleType: ContentScaleType)
}
