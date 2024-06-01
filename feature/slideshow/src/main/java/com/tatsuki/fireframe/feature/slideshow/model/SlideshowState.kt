package com.tatsuki.fireframe.feature.slideshow.model

import android.net.Uri
import com.tatsuki.fireframe.core.model.ContentScaleType
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.core.model.SlideshowInterval

data class SlideshowState(
    val slideshowInterval: SlideshowInterval,
    val contentScaleType: ContentScaleType,
    val slideImages: List<SlideImage>,
    val currentAndForecastWeather: CurrentAndForecastWeather?,
) {
    companion object {
        fun create(): SlideshowState {
            return SlideshowState(
                slideshowInterval = SlideshowInterval.ONE_MINUTE,
                contentScaleType = ContentScaleType.CROP,
                slideImages = emptyList(),
                currentAndForecastWeather = null,
            )
        }

        fun fake(
            slideshowInterval: SlideshowInterval = SlideshowInterval.ONE_MINUTE,
            contentScaleType: ContentScaleType = ContentScaleType.CROP,
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
                slideshowInterval = slideshowInterval,
                contentScaleType = contentScaleType,
                slideImages = slideImages,
                currentAndForecastWeather = currentAndForecastWeather,
            )
        }
    }
}
