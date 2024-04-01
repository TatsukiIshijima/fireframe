package com.tatsuki.fireframe.core.ui

import android.widget.TextClock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.datetime.TimeZone

@Composable
fun TextClock(
    modifier: Modifier = Modifier,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextClock(context).apply {
                this.timeZone = timeZone.id
                this.format12Hour = "HH:mm"
            }
        },
    )
}

@Preview
@Composable
private fun TextClockPreview() {
    MaterialTheme {
        Surface {
            TextClock()
        }
    }
}
