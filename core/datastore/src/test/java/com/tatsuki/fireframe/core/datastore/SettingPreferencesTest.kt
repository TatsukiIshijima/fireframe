package com.tatsuki.fireframe.core.datastore

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.tatsuki.fireframe.core.model.Location
import com.tatsuki.fireframe.core.testing.util.HiltAndroidAutoInjectRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertEquals

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class SettingPreferencesTest {

    @get:Rule
    val hiltAndroidAutoInjectRule = HiltAndroidAutoInjectRule(this)

    @Inject
    lateinit var settingPreferences: SettingPreferences

    @Test
    fun flowCurrentLocationWhenUpdateLocation() = runTest {
        settingPreferences.locationFlow.test {
            settingPreferences.updateLocation(
                Location(
                    latitude = 35.6800897,
                    longitude = 139.7654783,
                ),
            )
            val currentLocation = awaitItem()
            assertEquals(
                Location(
                    latitude = 35.6800897,
                    longitude = 139.7654783,
                ),
                currentLocation,
            )
        }
    }
}
