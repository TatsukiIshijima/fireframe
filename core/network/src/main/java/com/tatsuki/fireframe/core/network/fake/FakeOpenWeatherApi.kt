package com.tatsuki.fireframe.core.network.fake

import com.tatsuki.fireframe.core.network.OpenWeatherApi
import com.tatsuki.fireframe.core.network.model.OneCallResponse

class FakeOpenWeatherApi : OpenWeatherApi {

    override suspend fun oneCallCurrent(
        latitude: Double,
        longitude: Double,
        language: String,
        exclude: String,
        units: String,
        apiKey: String,
    ): OneCallResponse {
        TODO("Not yet implemented")
    }
}
