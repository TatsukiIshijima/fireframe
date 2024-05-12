package com.tatsuki.fireframe.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tatsuki.fireframe.core.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SettingPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SettingPreferences {

    private val Context.dataStore by preferencesDataStore(FILE_NAME)
    private val locationPreferencesKey = stringPreferencesKey(KEY_LOCATION)
    private val selectedSlideGroupIdPreferencesKey = longPreferencesKey(KEY_SELECTED_SLIDE_GROUP_ID)

    override val locationFlow: Flow<Location> =
        context.dataStore.data.map { preference ->
            preference[locationPreferencesKey]?.let { jsonString ->
                return@let Json.decodeFromString<Location>(jsonString)
            }
        }.filterNotNull()

    override val selectedSlideGroupIdFlow: Flow<Long> =
        context.dataStore.data.map { preference ->
            preference[selectedSlideGroupIdPreferencesKey] ?: -1L
        }

    override suspend fun updateLocation(location: Location) {
        context.dataStore.edit { preference ->
            val encodedValue = Json.encodeToString<Location>(location)
            preference[locationPreferencesKey] = encodedValue
        }
    }

    override suspend fun updateSelectedSlideGroupId(groupId: Long) {
        context.dataStore.edit { preference ->
            preference[selectedSlideGroupIdPreferencesKey] = groupId
        }
    }

    companion object {
        private const val FILE_NAME = "setting_preferences"
        private const val KEY_LOCATION = "location"
        private const val KEY_SELECTED_SLIDE_GROUP_ID = "selected_slide_group_id"
    }
}
