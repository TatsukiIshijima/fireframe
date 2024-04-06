package com.tatsuki.fireframe.feature.slideshow.ui

import android.graphics.Typeface
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatsuki.fireframe.core.ui.WeatherIconAsyncImage
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.ui.DateText
import com.tatsuki.fireframe.core.ui.HorizontalAutoLoopPager
import com.tatsuki.fireframe.core.ui.TextClock
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun SlideshowRoute(
    modifier: Modifier = Modifier,
) {
    SlideshowScreen(
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SlideshowScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        HorizontalAutoLoopPager(
            pageCount = 10,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            Text(
                text = "Page: ${page + 1}",
                modifier = Modifier,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd),
        ) {
            BatteryIcon(
                modifier = Modifier
                    .size(58.dp)
                    .padding(16.dp),
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart),
        ) {
            DateInfoShortPanel()
        }
    }
}

@Composable
private fun BatteryIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = android.R.drawable.ic_lock_idle_low_battery),
        contentDescription = null,
        modifier = modifier,
    )
}

@Composable
private fun DateInfoShortPanel(
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
            WeatherIconAsyncImage(
                imageUrl = "https://openweathermap.org/img/wn/11d@2x.png",
                contentDescription = null,
                placeHolder = {
                    Text(
                        text = "ー",
                        modifier = baseModifier.padding(end = 12.dp),
                    )
                },
                modifier = baseModifier.size(42.dp),
            )
            Text(
                text = "13°",
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
    FireframeTheme {
        SlideshowScreen()
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
fun SlideshowScreenMobilePreview() {
    FireframeTheme {
        SlideshowScreen()
    }
}
