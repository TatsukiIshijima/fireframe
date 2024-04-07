package com.tatsuki.fireframe.core.network.fake

import JvmUnitTestFakeAssetManager
import com.tatsuki.fireframe.core.common.network.Dispatcher
import com.tatsuki.fireframe.core.common.network.FireframeDispatchers.IO
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.core.network.OpenWeatherApi
import com.tatsuki.fireframe.core.network.model.OneCallResponse
import com.tatsuki.fireframe.core.network.model.toCurrentAndForecastWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class FakeOpenWeatherApi @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : OpenWeatherApi {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun fetchCurrentAndForecastWeather(
        latitude: Double,
        longitude: Double,
    ): CurrentAndForecastWeather {
        return withContext(ioDispatcher) {
            assets.open("one_call_response.json")
                .use { inputStream ->
                    networkJson
                        .decodeFromStream<OneCallResponse>(inputStream)
                        .toCurrentAndForecastWeather()
                }
        }
    }
}
