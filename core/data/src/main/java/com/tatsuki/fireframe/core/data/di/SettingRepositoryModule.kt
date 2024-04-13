package com.tatsuki.fireframe.core.data.di

import com.tatsuki.fireframe.core.data.repository.SettingRepository
import com.tatsuki.fireframe.core.data.repository.SettingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SettingRepositoryModule {

    @Binds
    @Singleton
    fun bindSettingRepository(
        settingRepositoryImpl: SettingRepositoryImpl,
    ): SettingRepository
}