package com.tatsuki.fireframe.core.ui

import android.graphics.Color
import android.graphics.Typeface
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
    textColorResource: Int = Color.BLACK,
    textSize: Float = 16f,
    typefaceStyle: Typeface = Typeface.DEFAULT,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextClock(context).apply {
                this.timeZone = timeZone.id
                format12Hour = "HH:mm"
                setTextColor(textColorResource)
                this.textSize = textSize
                setTypeface(this.typeface, typefaceStyle.style)
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
