package com.tatsuki.fireframe.core.data.model

data class CurrentWeather(
    val temperature: Double,
    val weatherDatas: List<WeatherData>,
)
