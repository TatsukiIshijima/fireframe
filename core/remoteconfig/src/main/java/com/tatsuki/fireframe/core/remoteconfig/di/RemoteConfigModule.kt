package com.tatsuki.fireframe.core.remoteconfig.di

import com.tatsuki.fireframe.core.remoteconfig.DefaultFireframeRemoteConfig
import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteConfigModule {

    @Binds
    @Singleton
    fun bindFireframeRemoteConfig(
        defaultFireframeRemoteConfig: DefaultFireframeRemoteConfig
    ): FireframeRemoteConfig
}