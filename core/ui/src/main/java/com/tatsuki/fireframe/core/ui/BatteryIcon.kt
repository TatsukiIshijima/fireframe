package com.tatsuki.fireframe.core.ui

import android.graphics.drawable.LevelListDrawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap

@Composable
fun BatteryIcon(
    level: Int,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val batteryIcons = LevelListDrawable().apply {
        addLevel(0, 1, context.getDrawable(R.drawable.outline_battery_horiz_000_24))
        addLevel(1, 10, context.getDrawable(R.drawable.outline_battery_very_low_24))
        addLevel(10, 25, context.getDrawable(R.drawable.outline_battery_low_24))
        addLevel(25, 50, context.getDrawable(R.drawable.outline_battery_horiz_050_24))
        addLevel(50, 75, context.getDrawable(R.drawable.outline_battery_horiz_075_24))
        addLevel(75, 100, context.getDrawable(R.drawable.outline_battery_full_alt_24))
    }
    batteryIcons.level = level

    Image(
        bitmap = batteryIcons.current.toBitmap().asImageBitmap(),
        contentDescription = null,
        modifier = modifier,
    )
}
