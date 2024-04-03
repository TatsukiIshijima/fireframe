package com.tatsuki.fireframe.feature.slideshow.ui

import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatsuki.fireframe.core.designsystem.theme.FireframeTheme
import com.tatsuki.fireframe.core.ui.DateText
import com.tatsuki.fireframe.core.ui.TextClock
import com.tatsuki.fireframe.feature.slideshow.R

@Composable
internal fun SlideshowRoute(
    modifier: Modifier = Modifier,
) {
    SlideshowScreen(
        modifier = modifier,
    )
}

@Composable
internal fun SlideshowScreen(
    modifier: Modifier = Modifier,
    batteryIconAlignment: Alignment = Alignment.TopEnd,
    dateInfoShortPanelAlignment: Alignment = Alignment.BottomStart,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .align(batteryIconAlignment),
        ) {
            BatteryIcon(
                modifier = Modifier
                    .size(54.dp)
                    .padding(16.dp),
            )
        }
        Box(
            modifier = Modifier
                .align(dateInfoShortPanelAlignment),
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val baseModifier = remember {
                Modifier.alignByBaseline()
            }
            Text(
                text = "13°",
                modifier = baseModifier,
                fontSize = 24.sp
            )
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
