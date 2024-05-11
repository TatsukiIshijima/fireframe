package com.tatsuki.fireframe

import android.app.Application
import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FireframeApplication : Application() {

    @Inject
    lateinit var fireframeRemoteConfig: FireframeRemoteConfig

    override fun onCreate() {
        super.onCreate()

        fireframeRemoteConfig.fetchAndActivate()
    }
}
