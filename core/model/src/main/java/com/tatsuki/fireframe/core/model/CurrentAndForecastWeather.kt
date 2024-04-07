package com.tatsuki.fireframe.core.model

data class CurrentAndForecastWeather(
    val currentWeather: CurrentWeather,
    val dailyWeathers: List<DailyWeather>,
)
