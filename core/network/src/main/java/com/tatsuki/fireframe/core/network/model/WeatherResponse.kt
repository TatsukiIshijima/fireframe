package com.tatsuki.fireframe.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("main")
    val main: String,

    @SerialName("icon")
    val icon: String,
)
