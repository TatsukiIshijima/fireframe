package com.tatsuki.fireframe.feature.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.WeatherRepository
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    private val mutableCurrentAndForecastWeather =
        MutableStateFlow<CurrentAndForecastWeather?>(null)
    val currentAndForecastWeather = mutableCurrentAndForecastWeather.asStateFlow()

    fun fetchCurrentAndForecastWeather() {
        viewModelScope.launch {
            try {
                val response = weatherRepository.fetchCurrentAndForecastWeather(
                    latitude = 35.681236,
                    longitude = 139.767125,
                )
                mutableCurrentAndForecastWeather.value = response
            } catch (e: Exception) {
                coroutineContext.ensureActive()
            }
        }
    }
}
