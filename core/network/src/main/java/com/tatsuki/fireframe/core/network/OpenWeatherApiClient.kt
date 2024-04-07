package com.tatsuki.fireframe.core.network

import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.core.network.model.toCurrentAndForecastWeather
import javax.inject.Inject

class OpenWeatherApiClient @Inject constructor(
    private val service: OpenWeatherApiService,
) : OpenWeatherApi {

    override suspend fun fetchCurrentAndForecastWeather(
        latitude: Double,
        longitude: Double,
    ): CurrentAndForecastWeather {
        return service.oneCallCurrent(
            latitude = latitude,
            longitude = longitude,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY,
        ).toCurrentAndForecastWeather()
    }
}
