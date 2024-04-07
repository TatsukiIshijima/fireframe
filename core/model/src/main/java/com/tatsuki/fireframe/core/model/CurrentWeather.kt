package com.tatsuki.fireframe.core.model

data class CurrentWeather(
    val temperature: Double,
    val weatherDatas: List<WeatherData>,
)
