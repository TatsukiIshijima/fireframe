package com.tatsuki.fireframe.core.data.di

import com.tatsuki.fireframe.core.data.repository.WeatherRepository
import com.tatsuki.fireframe.core.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WeatherRepositoryModule {

    @Binds
    fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl,
    ): WeatherRepository
}
