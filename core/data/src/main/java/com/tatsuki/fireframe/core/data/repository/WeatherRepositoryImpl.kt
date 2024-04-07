package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.core.network.OpenWeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
) : WeatherRepository {

    override suspend fun fetchCurrentAndForecastWeather(
        latitude: Double,
        longitude: Double,
    ): CurrentAndForecastWeather {
        return openWeatherApi.fetchCurrentAndForecastWeather(
            latitude = latitude,
            longitude = longitude,
        )
    }
}
