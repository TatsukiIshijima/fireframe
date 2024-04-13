package com.tatsuki.fireframe.feature.slideshow.ui

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.model.CurrentAndForecastWeather
import com.tatsuki.fireframe.core.ui.AsyncImage
import com.tatsuki.fireframe.core.ui.DateText
import com.tatsuki.fireframe.core.ui.HorizontalAutoLoopPager
import com.tatsuki.fireframe.core.ui.TextClock
import com.tatsuki.fireframe.feature.slideshow.R
import com.tatsuki.fireframe.feature.slideshow.SlideshowViewModel
import com.tatsuki.fireframe.core.ui.R as designSystemR

@Composable
internal fun SlideshowRoute(
    modifier: Modifier = Modifier,
    slideshowViewModel: SlideshowViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val batteryIcons = LevelListDrawable().apply {
        addLevel(0, 1, context.getDrawable(designSystemR.drawable.outline_battery_horiz_000_24))
        addLevel(1, 10, context.getDrawable(designSystemR.drawable.outline_battery_very_low_24))
        addLevel(10, 25, context.getDrawable(designSystemR.drawable.outline_battery_low_24))
        addLevel(25, 50, context.getDrawable(designSystemR.drawable.outline_battery_horiz_050_24))
        addLevel(50, 99, context.getDrawable(designSystemR.drawable.outline_battery_horiz_075_24))
        addLevel(99, 100, context.getDrawable(designSystemR.drawable.outline_battery_full_alt_24))
    }

    batteryIcons.level = 50

    val photoUrls = listOf(
        "https://pelipecky.sk/wp-content/uploads/2020/03/cesta-domov-400x240.jpg",
        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG",
        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG",
        "https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RLB_486265291EDR_F0481570RHAZ00323M_.JPG",
        "https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/rcam/RRB_486265291EDR_F0481570RHAZ00323M_.JPG",
        "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg",
        "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg",
        "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631290503689E01_DXXX.jpg",
        "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631290305226E03_DXXX.jpg",
        "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631280503688E0B_DXXX.jpg",
        "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631280305225E03_DXXX.jpg",
    )
    val currentAndForecastWeather by slideshowViewModel.currentAndForecastWeather.collectAsState()

    SlideshowScreen(
        batteryIcon = batteryIcons.current,
        photoUrls = photoUrls,
        currentAndForecastWeather = currentAndForecastWeather,
        modifier = modifier,
    )

    LaunchedEffect(key1 = Unit) {
        slideshowViewModel.fetchCurrentAndForecastWeather()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SlideshowScreen(
    batteryIcon: Drawable,
    photoUrls: List<Any?>,
    currentAndForecastWeather: CurrentAndForecastWeather?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (photoUrls.isEmpty()) {
            // TODO: Add error handling
        } else {
            HorizontalAutoLoopPager(
                pageCount = photoUrls.size,
                modifier = Modifier.fillMaxSize(),
                beyondBoundsPageCount = 1,
                delayMills = 5000,
            ) { page ->
                AsyncImage(
                    model = photoUrls[page],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.Low,
                    placeHolder = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd),
        ) {
            BatteryIcon(
                icon = batteryIcon,
                modifier = Modifier
                    .size(58.dp)
                    .padding(16.dp),
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart),
        ) {
            DateInfoShortPanel(
                currentAndForecastWeather = currentAndForecastWeather,
            )
        }
    }
}

@Composable
private fun BatteryIcon(
    icon: Drawable,
    modifier: Modifier = Modifier,
) {
    val bitmap = icon.toBitmap().asImageBitmap()
    Image(
        bitmap = bitmap,
        contentDescription = null,
        modifier = modifier,
    )
}

@Composable
private fun DateInfoShortPanel(
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

            if (currentAndForecastWeather == null) {
                Text(
                    text = "ー",
                    modifier = baseModifier.padding(end = 12.dp),
                )
            } else {
                AsyncImage(
                    model = currentAndForecastWeather.currentWeather.weatherDataList.first().iconUrl,
                    contentDescription = null,
                    placeHolder = {
                        Text(
                            text = "ー",
                            modifier = baseModifier.padding(end = 12.dp),
                        )
                    },
                    modifier = baseModifier.size(42.dp),
                )
            }

            val temperature =
                currentAndForecastWeather?.currentWeather?.temperature?.toString() ?: "ー"

            Text(
                text = temperature,
                modifier = baseModifier,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(12.dp))
            DateText(
                modifier = baseModifier,
                fontSize = 24.sp,
            )
        }
        TextClock(
            modifier = Modifier,
            textColorResource = LocalContentColor.current.toArgb(),
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
    val context = LocalContext.current
    val iconDrawable = context.getDrawable(designSystemR.drawable.outline_battery_full_alt_24)
    FireframeTheme {
        SlideshowScreen(
            batteryIcon = iconDrawable!!,
            photoUrls = listOf(
                R.drawable.dummy_image,
            ),
            currentAndForecastWeather = null,
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
fun SlideshowScreenMobilePreview() {
    val context = LocalContext.current
    val iconDrawable = context.getDrawable(designSystemR.drawable.outline_battery_full_alt_24)
    FireframeTheme {
        SlideshowScreen(
            batteryIcon = iconDrawable!!,
            photoUrls = listOf(
                R.drawable.dummy_image,
            ),
            currentAndForecastWeather = null,
        )
    }
}
