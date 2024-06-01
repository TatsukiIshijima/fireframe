package com.tatsuki.fireframe.feature.slideshow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.core.data.repository.SettingRepository
import com.tatsuki.fireframe.core.data.repository.WeatherRepository
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.feature.slideshow.model.SlideImage
import com.tatsuki.fireframe.feature.slideshow.model.SlideshowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val settingRepository: SettingRepository,
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    private val selectedSlideshowInterval = settingRepository.selectedSlideshowInterval
    private val selectedContentScaleType = settingRepository.selectedContentScaleType
    private val mutableSlideImages = MutableStateFlow<List<SlideImage>>(emptyList())
    private val mutableCurrentAndForecastWeather =
        MutableStateFlow<CurrentAndForecastWeather?>(null)

    val slideshowState = combine(
        selectedSlideshowInterval,
        selectedContentScaleType,
        mutableSlideImages,
        mutableCurrentAndForecastWeather,
    ) { slideshowInterval, contentScaleType, slideImages, currentAndForecastWeather ->
        SlideshowState(
            slideshowInterval = slideshowInterval,
            contentScaleType = contentScaleType,
            slideImages = slideImages,
            currentAndForecastWeather = currentAndForecastWeather,
        )
    }

    fun onCreate() {
        loadSlideGroup()
    }

    private fun loadSlideGroup() {
        viewModelScope.launch {
            try {
                val selectedSlideGroupId = settingRepository.selectedSlideGroupIdFlow.first()
                val slideImages = mediaRepository.getSlideGroupImages(selectedSlideGroupId)
                    .map { slideGroupImage -> SlideImage.from(slideGroupImage) }
                mutableSlideImages.value = slideImages
            } catch (e: Exception) {
                Log.e("SlideshowViewModel", "Failed to load selected images", e)
            }
        }
    }

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
