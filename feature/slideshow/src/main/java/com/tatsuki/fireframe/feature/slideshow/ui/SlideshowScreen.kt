package com.tatsuki.fireframe.feature.slideshow.ui

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tatsuki.fireframe.core.designsystem.component.FireframeAsyncImage
import com.tatsuki.fireframe.core.designsystem.component.Placeholder
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.core.ui.DateText
import com.tatsuki.fireframe.core.ui.HorizontalAutoLoopPager
import com.tatsuki.fireframe.core.ui.TextClock
import com.tatsuki.fireframe.core.ui.WeatherIcon
import com.tatsuki.fireframe.feature.slideshow.R
import com.tatsuki.fireframe.feature.slideshow.SlideshowViewModel
import com.tatsuki.fireframe.feature.slideshow.model.SlideshowState

@Composable
internal fun SlideshowRoute(
    isEnableWeather: Boolean,
    modifier: Modifier = Modifier,
    slideshowViewModel: SlideshowViewModel = hiltViewModel(),
) {
    val slideshowState by slideshowViewModel.slideshowState.collectAsStateWithLifecycle(
        initialValue = SlideshowState.create(),
    )

    SlideshowScreen(
        isEnableWeather = isEnableWeather,
        slideshowState = slideshowState,
        modifier = modifier,
    )

    LaunchedEffect(Unit) {
        slideshowViewModel.onCreate()
    }

    // FIXME: prevent call twice
    if (isEnableWeather) {
        LaunchedEffect(Unit) {
            Log.d("SlideshowRoute", "LaunchedEffect")
            slideshowViewModel.fetchCurrentAndForecastWeather()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SlideshowScreen(
    isEnableWeather: Boolean,
    slideshowState: SlideshowState,
    modifier: Modifier = Modifier,
) {
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (slideshowState.slideImages.isEmpty()) {
            // TODO: Add error handling
        } else {
            HorizontalAutoLoopPager(
                pageCount = slideshowState.slideImages.size,
                modifier = Modifier.fillMaxSize(),
                beyondBoundsPageCount = 1,
                delayMills = 5000,
            ) { page ->
                if (isLocalInspection) {
                    Placeholder(
                        modifier = Modifier.fillMaxSize(),
                        text = "Image$page",
                    )
                } else {
                    FireframeAsyncImage(
                        model = slideshowState.slideImages[page].uri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart),
        ) {
            DateInfoShortPanel(
                isEnableWeather = isEnableWeather,
                currentAndForecastWeather = slideshowState.currentAndForecastWeather,
            )
        }
    }
}

@Composable
private fun DateInfoShortPanel(
    isEnableWeather: Boolean,
    currentAndForecastWeather: CurrentAndForecastWeather?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val baseModifier = remember {
                Modifier.alignByBaseline()
            }

            if (isEnableWeather) {
                if (currentAndForecastWeather == null) {
                    Text(
                        text = "ー",
                        modifier = baseModifier.padding(end = 12.dp),
                    )
                } else {
                    WeatherIcon(
                        weatherId = currentAndForecastWeather.currentWeather.weatherDataList.first().id,
                        modifier = baseModifier.size(42.dp),
                    )
                }

                val temperature =
                    currentAndForecastWeather?.currentWeather?.temperature?.toInt()
                val temperatureText = if (temperature != null) {
                    stringResource(id = R.string.unit_celsius, temperature)
                } else {
                    "-"
                }

                Text(
                    text = temperatureText,
                    modifier = baseModifier,
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            DateText(
                modifier = baseModifier,
                fontSize = 24.sp,
            )
        }
        TextClock(
            modifier = Modifier,
            textColorResource = Color.WHITE,
            textSize = 80f,
            typefaceStyle = Typeface.DEFAULT_BOLD,
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_TABLET,
)
@Composable
fun SlideshowScreenTabletPreview() {
    FireframeTheme {
        SlideshowScreen(
            isEnableWeather = true,
            slideshowState = SlideshowState.fake(),
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
fun SlideshowScreenMobilePreview() {
    FireframeTheme {
        SlideshowScreen(
            isEnableWeather = true,
            slideshowState = SlideshowState.fake(),
        )
    }
}
