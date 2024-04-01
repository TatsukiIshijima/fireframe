package com.tatsuki.fireframe.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

object Date {
    fun currentLocalDateTime(): LocalDateTime {
        val timeZone = TimeZone.currentSystemDefault()
        return Clock.System.now().toLocalDateTime(timeZone)
    }

    fun formattedCurrentLocalDate(): String {
        val formatter = DateTimeFormatter.ofPattern("M月d日（E）")
        return formatter.format(currentLocalDateTime().toJavaLocalDateTime())
    }
}