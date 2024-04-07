package com.tatsuki.fireframe.core.network.model

import com.tatsuki.fireframe.core.model.CurrentWeather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(
    @SerialName("dt")
    val currentTime: Int,

    @SerialName("temp")
    val temperature: Double,

    @SerialName("weather")
    val weather: List<WeatherResponse>,
)

internal fun CurrentResponse.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        temperature = temperature,
        weatherDatas = weather.map { it.toWeatherData() },
    )
}
