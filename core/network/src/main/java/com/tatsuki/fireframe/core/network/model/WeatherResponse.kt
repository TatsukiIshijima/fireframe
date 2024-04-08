package com.tatsuki.fireframe.core.network.model

import com.tatsuki.fireframe.core.model.WeatherData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("main")
    val main: String,

    @SerialName("icon")
    val icon: String,
) {
    internal fun buildIconUrl(): String {
        return String.format("%s%s%s%s", OPEN_WEATHER_DOMAIN, OPEN_WEATHER_ICON_PATH, icon, OPEN_WEATHER_ICON_EXTENSION)
    }

    companion object {
        private const val OPEN_WEATHER_DOMAIN = "https://openweathermap.org/"
        private const val OPEN_WEATHER_ICON_PATH = "img/wn/"
        private const val OPEN_WEATHER_ICON_EXTENSION = "@2x.png"
    }
}

internal fun WeatherResponse.toWeatherData(): WeatherData {
    return WeatherData(
        id = id,
        group = main,
        iconUrl = buildIconUrl(),
    )
}
