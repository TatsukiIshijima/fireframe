package com.tatsuki.fireframe.core.network

import com.tatsuki.fireframe.core.testing.util.HiltAndroidAutoInjectRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
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

    @get:Rule
    val hiltAndroidAutoInjectRule = HiltAndroidAutoInjectRule(this)

    @Inject
    lateinit var openWeatherApi: OpenWeatherApi

    @Test
    fun testFetchCurrentWeather() = runTest {
        val response = openWeatherApi.fetchCurrentWeather(
            latitude = 35.6800897,
            longitude = 139.7654783,
        )
        assert(response.current.weather.isNotEmpty())
        assert(response.current.weather.first().id == 501)
        assert(response.current.weather.first().main == "Rain")
        assert(response.current.weather.first().icon == "10d")
        assert(response.daily.isNotEmpty())
        assert(response.daily.first().weather.isNotEmpty())
        assert(response.daily.first().weather.first().id == 502)
        assert(response.daily.first().weather.first().main == "Rain")
        assert(response.daily.first().weather.first().icon == "10d")
    }
}
