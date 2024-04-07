package com.tatsuki.fireframe.core.data.model

data class DailyWeather(
    // FIXME: Change type to Date.
    val time: Long,
    val temperature: Double,
    val weatherDataList: List<WeatherData>,
)
