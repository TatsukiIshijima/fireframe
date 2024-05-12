package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.Location
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    val locationFlow: Flow<Location>

    val selectedSlideGroupIdFlow: Flow<Long>

    suspend fun updateLocation(location: Location)

    suspend fun updateSelectedSlideGroupId(groupId: Long)
}
