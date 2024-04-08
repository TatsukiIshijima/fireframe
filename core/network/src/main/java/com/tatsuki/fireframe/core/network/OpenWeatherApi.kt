package com.tatsuki.fireframe.core.network

import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather

interface OpenWeatherApi {

    /**
     * Fetches the current weather data.
     *
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The current weather data.
     */
    suspend fun fetchCurrentAndForecastWeather(
        latitude: Double,
        longitude: Double,
    ): CurrentAndForecastWeather
}
