package com.tatsuki.fireframe.core.network.fake

import com.tatsuki.fireframe.core.network.OpenWeatherApi
import com.tatsuki.fireframe.core.network.model.OneCallResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class FakeOpenWeatherApi @Inject constructor(
    private val networkJson: Json,
    private val assets: FakeAssetManager,
) : OpenWeatherApi {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): OneCallResponse {
        return assets.open("one_call_response.json")
            .use(networkJson::decodeFromStream)
    }
}
