package com.tatsuki.fireframe.core.model

data class CurrentAndForecastWeather(
    val currentWeather: CurrentWeather,
    val dailyWeathers: List<DailyWeather>,
) {
    companion object {
        fun fake(
            currentWeather: CurrentWeather = CurrentWeather.fake(),
            dailyWeathers: List<DailyWeather> = listOf(DailyWeather.fake()),
        ): CurrentAndForecastWeather {
            return CurrentAndForecastWeather(
                currentWeather = currentWeather,
                dailyWeathers = dailyWeathers,
            )
        }
    }
}
