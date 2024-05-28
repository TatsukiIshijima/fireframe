package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.datastore.SettingPreferences
import com.tatsuki.fireframe.core.model.ContentScaleType
import com.tatsuki.fireframe.core.model.Location
import com.tatsuki.fireframe.core.model.SlideshowInterval
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingPreferences: SettingPreferences,
) : SettingRepository {

    override val locationFlow: Flow<Location> = settingPreferences.locationFlow

    override val selectedSlideGroupIdFlow: Flow<Long> = settingPreferences.selectedSlideGroupIdFlow

    override val selectedSlideshowInterval: Flow<SlideshowInterval> =
        settingPreferences.selectedSlideshowInterval

    override val selectedContentScaleType: Flow<ContentScaleType> =
        settingPreferences.selectedContentScaleType

    override suspend fun updateLocation(location: Location) {
        settingPreferences.updateLocation(location)
    }

    override suspend fun updateSelectedSlideGroupId(groupId: Long) {
        settingPreferences.updateSelectedSlideGroupId(groupId)
    }

    override suspend fun updateSelectedSlideshowInterval(slideshowInterval: SlideshowInterval) {
        settingPreferences.updateSelectedSlideshowInterval(slideshowInterval)
    }

    override suspend fun updateSelectedContentScaleType(contentScaleType: ContentScaleType) {
        settingPreferences.updateSelectedContentScaleType(contentScaleType)
    }
}
