package com.tatsuki.fireframe.core.model

data class WeatherData(
    val id: Int,
    val group: String,
    val iconUrl: String,
) {
    companion object {
        fun fake(
            id: Int = 501,
            group: String = "Rain",
            iconUrl: String = "https://openweathermap.org/img/wn/10d@2x.png",
        ) = WeatherData(
            id = id,
            group = group,
            iconUrl = iconUrl,
        )
    }
}
