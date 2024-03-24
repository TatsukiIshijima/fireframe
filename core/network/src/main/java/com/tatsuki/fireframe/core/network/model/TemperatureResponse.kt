package com.tatsuki.fireframe.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemperatureResponse(
    @SerialName("day")
    val day: Double,
)
