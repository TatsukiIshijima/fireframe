package com.tatsuki.fireframe.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tatsuki.fireframe.core.model.Date
import kotlinx.datetime.DayOfWeek
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.format.TextStyle.SHORT
import java.util.Locale

@Composable
fun DateText(
    modifier: Modifier = Modifier,
) {
    val date = Date.formattedCurrentLocalDate()
    Text(
        text = date,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun DateTextPreview() {
    MaterialTheme {
        Surface {
            DateText()
        }
    }
}