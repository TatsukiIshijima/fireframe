package com.tatsuki.fireframe.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(
    @SerialName("dt")
    val currentTime: Int,

    @SerialName("temp")
    val temperature: Double,

    @SerialName("weather")
    val weather: List<WeatherResponse>
)