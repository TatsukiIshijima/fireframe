package com.tatsuki.fireframe.feature.slideshow.model

import android.net.Uri
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather

data class SlideshowState(
    val slideImages: List<SlideImage>,
    val currentAndForecastWeather: CurrentAndForecastWeather?,
) {
    companion object {
        fun create(): SlideshowState {
            return SlideshowState(
                slideImages = emptyList(),
                currentAndForecastWeather = null,
            )
        }

        fun fake(
            slideImages: List<SlideImage> = listOf(
                SlideImage.fake(
                    id = 1,
                    uri = Uri.EMPTY,
                ),
                SlideImage.fake(
                    id = 2,
                    uri = Uri.EMPTY,
                ),
                SlideImage.fake(
                    id = 3,
                    uri = Uri.EMPTY,
                ),
            ),
            currentAndForecastWeather: CurrentAndForecastWeather = CurrentAndForecastWeather.fake(),
        ): SlideshowState {
            return SlideshowState(
                slideImages = slideImages,
                currentAndForecastWeather = currentAndForecastWeather,
            )
        }
    }
}
