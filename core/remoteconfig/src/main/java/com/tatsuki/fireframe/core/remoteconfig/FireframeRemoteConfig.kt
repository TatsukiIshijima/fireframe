package com.tatsuki.fireframe.core.remoteconfig

interface FireframeRemoteConfig {

    fun fetchAndActivate()

    fun isEnableWeather(): Boolean
}