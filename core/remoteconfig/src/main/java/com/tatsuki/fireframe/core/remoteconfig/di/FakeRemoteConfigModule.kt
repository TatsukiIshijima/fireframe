package com.tatsuki.fireframe.core.remoteconfig.di

import com.tatsuki.fireframe.core.remoteconfig.FireframeRemoteConfig
import com.tatsuki.fireframe.core.remoteconfig.fake.FakeRemoteConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RemoteConfigModule::class],
)
interface FakeRemoteConfigModule {

    @Binds
    @Singleton
    fun bindFakeRemoteConfig(
        fakeRemoteConfig: FakeRemoteConfig,
    ): FireframeRemoteConfig
}
