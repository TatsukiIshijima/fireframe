package com.tatsuki.fireframe.core.remoteconfig.fake

import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import javax.inject.Inject

class FakeRemoteConfig @Inject constructor() : FireframeRemoteConfig {

    override fun fetchAndActivate() {
        // Do nothing
    }

    override fun isEnableWeather(): Boolean {
        return false
    }
}
