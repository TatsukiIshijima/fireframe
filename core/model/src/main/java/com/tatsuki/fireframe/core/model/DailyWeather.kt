package com.tatsuki.fireframe.core.model

data class DailyWeather(
    // FIXME: Change type to Date.
    val time: Long,
    val temperature: Double,
    val weatherDataList: List<WeatherData>,
) {
    companion object {
        fun fake(
            time: Long = 1711414083,
            temperature: Double = 10.53,
            weatherDataList: List<WeatherData> = listOf(WeatherData.fake()),
        ): DailyWeather {
            return DailyWeather(
                time = time,
                temperature = temperature,
                weatherDataList = weatherDataList,
            )
        }
    }
}
