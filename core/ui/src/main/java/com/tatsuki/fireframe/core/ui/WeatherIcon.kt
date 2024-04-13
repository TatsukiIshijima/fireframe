package com.tatsuki.fireframe.core.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun WeatherIcon(
    weatherId: Int,
    modifier: Modifier = Modifier,
) {
    // https://erikflowers.github.io/weather-icons/api-list.html
    val iconResId = when (weatherId) {
        in 200..202, in 230..232 -> R.drawable.wi_thunderstorm
        in 210..221 -> R.drawable.wi_lightning
        in 300..301, 321, 500 -> R.drawable.wi_sprinkle
        302, in 311..312, 314, in 501..504 -> R.drawable.wi_rain
        310, 511, in 611..620 -> R.drawable.wi_rain_mix
        313, in 520..522, 701 -> R.drawable.wi_showers
        531, 901 -> R.drawable.wi_storm_showers
        in 600..601, in 621..622 -> R.drawable.wi_snow
        602 -> R.drawable.wi_sleet
        711 -> R.drawable.wi_smoke
        721 -> R.drawable.wi_day_haze
        731 -> R.drawable.wi_dust
        741 -> R.drawable.wi_fog
        in 761..762 -> R.drawable.wi_dust
        771, in 801..803 -> R.drawable.wi_cloudy_gusts
        781, 900 -> R.drawable.wi_tornado
        800 -> R.drawable.wi_day_sunny
        804 -> R.drawable.wi_cloudy
        902 -> R.drawable.wi_hurricane
        903 -> R.drawable.wi_snowflake_cold
        904 -> R.drawable.wi_hot
        905 -> R.drawable.wi_windy
        906 -> R.drawable.wi_hail
        957 -> R.drawable.wi_strong_wind
        else -> R.drawable.wi_na
    }
    Image(
        painter = painterResource(id = iconResId),
        contentDescription = "Weather icon",
        modifier = modifier,
    )
}
