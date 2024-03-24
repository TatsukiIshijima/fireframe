package com.tatsuki.fireframe.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyResponse(
    @SerialName("dt")
    val time: Long,

    @SerialName("temp")
    val temperature: TemperatureResponse,

    @SerialName("weather")
    val weather: List<WeatherResponse>,
)