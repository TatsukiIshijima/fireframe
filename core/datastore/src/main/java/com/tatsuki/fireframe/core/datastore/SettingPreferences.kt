package com.tatsuki.fireframe.core.datastore

import com.tatsuki.fireframe.core.model.Location
import kotlinx.coroutines.flow.Flow

interface SettingPreferences {

    val locationFlow: Flow<Location?>

    suspend fun updateLocation(location: Location)
}
