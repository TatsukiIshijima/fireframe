package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.Location
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    val locationFlow: Flow<Location?>

    suspend fun updateLocation(location: Location)
}
