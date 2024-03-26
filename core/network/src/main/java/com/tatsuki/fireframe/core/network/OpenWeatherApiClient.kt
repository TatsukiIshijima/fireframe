package com.tatsuki.fireframe.core.network

import com.tatsuki.fireframe.core.network.model.OneCallResponse
import javax.inject.Inject

class OpenWeatherApiClient @Inject constructor(
    private val service: OpenWeatherApiService,
) : OpenWeatherApi {

    override suspend fun fetchCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): OneCallResponse {
        return service.oneCallCurrent(
            latitude = latitude,
            longitude = longitude,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY,
        )
    }
}
