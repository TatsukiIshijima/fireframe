package com.tatsuki.fireframe.core.network

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class FakeOpenWeatherApiTest {

    // FIXME: Apply hiltAndroidAutoInjectRule
    @get:Rule
    val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var openWeatherApi: OpenWeatherApi

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testGetWeather() = runTest {
        val response = openWeatherApi.oneCallCurrent(
            latitude = 35.6800897,
            longitude = 139.7654783,
        )
        println(response)
    }
}
