package com.tatsuki.fireframe.core.network.model

import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OneCallResponse(
    @SerialName("lat")
    val latitude: Double,

    @SerialName("lon")
    val longitude: Double,

    @SerialName("timezone")
    val timezone: String,

    @SerialName("timezone_offset")
    val timezoneOffset: Double,

    @SerialName("current")
    val current: CurrentResponse,

    @SerialName("daily")
    val daily: List<DailyResponse>,
)

internal fun OneCallResponse.toCurrentAndForecastWeather(): CurrentAndForecastWeather {
    return CurrentAndForecastWeather(
        currentWeather = current.toCurrentWeather(),
        dailyWeathers = daily.map { it.toDailyWeather() },
    )
}
