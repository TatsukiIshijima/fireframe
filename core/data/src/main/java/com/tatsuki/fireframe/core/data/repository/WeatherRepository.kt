package com.tatsuki.fireframe.core.data.repository

import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather

interface WeatherRepository {

    suspend fun fetchCurrentAndForecastWeather(
        latitude: Double,
        longitude: Double,
    ): CurrentAndForecastWeather
}
