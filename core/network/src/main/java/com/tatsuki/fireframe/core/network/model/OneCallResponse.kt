package com.tatsuki.fireframe.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OneCallResponse(
    @SerialName("lat")
    val latitude: Double,

    @SerialName("lon")
    val longitude: Double,

    @SerialName("timezone")
    val timezone: String,

    @SerialName("timezone_offset")
    val timezoneOffset: Double,

    @SerialName("current")
    val current: CurrentResponse,

    @SerialName("daily")
    val daily: List<DailyResponse>,
)
