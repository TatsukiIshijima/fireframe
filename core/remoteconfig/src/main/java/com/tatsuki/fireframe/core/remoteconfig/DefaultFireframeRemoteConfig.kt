package com.tatsuki.fireframe.core.remoteconfig

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DefaultFireframeRemoteConfig @Inject constructor() : FireframeRemoteConfig {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    override fun fetchAndActivate() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FireframeRemoteConfig", "Fetch and activate succeeded.")
                } else {
                    Log.e("FireframeRemoteConfig", "Fetch and activate failed. ${task.exception}")
                }
            }
    }

    override fun isEnableWeather(): Boolean {
        return remoteConfig.getBoolean(FireframeRemoteConfigKey.IS_ENABLE_WEATHER)
    }
}
