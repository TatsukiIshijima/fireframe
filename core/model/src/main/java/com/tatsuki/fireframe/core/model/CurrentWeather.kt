package com.tatsuki.fireframe.core.model

data class CurrentWeather(
    val temperature: Double,
    val weatherDataList: List<WeatherData>,
) {
    companion object {
        fun fake(
            temperature: Double = 10.53,
            weatherDataList: List<WeatherData> = listOf(WeatherData.fake()),
        ) = CurrentWeather(
            temperature = temperature,
            weatherDataList = weatherDataList,
        )
    }
}
