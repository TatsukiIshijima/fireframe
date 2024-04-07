package com.tatsuki.fireframe.core.data.model

data class CurrentAndForecastWeather(
    val currentWeather: CurrentWeather,
    val dailyWeatherList: List<DailyWeather>,
)
