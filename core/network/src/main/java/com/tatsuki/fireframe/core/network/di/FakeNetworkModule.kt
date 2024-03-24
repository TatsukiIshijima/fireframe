package com.tatsuki.fireframe.core.network.di

import com.tatsuki.fireframe.core.network.OpenWeatherApi
import com.tatsuki.fireframe.core.network.fake.FakeOpenWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [NetworkModule::class]
//)
//object FakeNetworkModule {
//
//    @Provides
//    @Singleton
//    fun openWeatherApi(): OpenWeatherApi {
//        return FakeOpenWeatherApi()
//    }
//}