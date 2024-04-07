package com.tatsuki.fireframe.core.model

data class DailyWeather(
    // FIXME: Change type to Date.
    val time: Long,
    val temperature: Double,
    val weatherDataList: List<WeatherData>,
)
