package com.tatsuki.fireframe.core.network.di

import android.content.Context
import com.tatsuki.fireframe.core.common.network.Dispatcher
import com.tatsuki.fireframe.core.common.network.FireframeDispatchers.IO
import com.tatsuki.fireframe.core.network.OpenWeatherApi
import com.tatsuki.fireframe.core.network.fake.FakeAssetManager
import com.tatsuki.fireframe.core.network.fake.FakeOpenWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FakeNetworkModule {

    @Provides
    @Singleton
    fun providesAssetManager(
        @ApplicationContext context: Context,
    ): FakeAssetManager {
        return FakeAssetManager(context.assets::open)
    }
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [OpenWeatherApiModule::class],
)
internal object FakeOpenWeatherApiModule {

    @Provides
    @Singleton
    fun openWeatherApi(
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        networkJson: Json,
        assets: FakeAssetManager,
    ): OpenWeatherApi {
        return FakeOpenWeatherApi(
            ioDispatcher = ioDispatcher,
            networkJson = networkJson,
            assets = assets,
        )
    }
}
