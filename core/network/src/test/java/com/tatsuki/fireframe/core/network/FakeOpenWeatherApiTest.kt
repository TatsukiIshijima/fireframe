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
        val result = openWeatherApi.fetchCurrentAndForecastWeather(
            latitude = 35.6800897,
            longitude = 139.7654783,
        )
        assert(result.currentWeather.weatherDatas.isNotEmpty())
        assert(result.currentWeather.weatherDatas.first().id == 501)
        assert(result.currentWeather.weatherDatas.first().group == "Rain")
        assert(
            result.currentWeather.weatherDatas.first().iconUrl ==
                "https://openweathermap.org/img/wn/10d@2x.png",
        )
        assert(result.dailyWeathers.isNotEmpty())
        assert(result.dailyWeathers.first().weatherDataList.isNotEmpty())
        assert(result.dailyWeathers.first().weatherDataList.first().id == 502)
        assert(result.dailyWeathers.first().weatherDataList.first().group == "Rain")
        assert(
            result.dailyWeathers.first().weatherDataList.first().iconUrl ==
                "https://openweathermap.org/img/wn/10d@2x.png",
        )
    }
}
