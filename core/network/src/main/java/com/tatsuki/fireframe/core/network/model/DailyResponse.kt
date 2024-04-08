package com.tatsuki.fireframe.core.network.model

import com.tatsuki.fireframe.core.model.DailyWeather
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

internal fun DailyResponse.toDailyWeather(): DailyWeather {
    return DailyWeather(
        time = time,
        temperature = temperature.day,
        weatherDataList = weather.map { it.toWeatherData() },
    )
}
