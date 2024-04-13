package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.datastore.SettingPreferences
import com.tatsuki.fireframe.core.model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingPreferences: SettingPreferences,
) : SettingRepository {

    override val locationFlow: Flow<Location?> = settingPreferences.locationFlow

    override suspend fun updateLocation(location: Location) {
        settingPreferences.updateLocation(location)
    }
}
