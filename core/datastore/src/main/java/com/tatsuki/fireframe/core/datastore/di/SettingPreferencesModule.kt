package com.tatsuki.fireframe.core.datastore.di

import com.tatsuki.fireframe.core.datastore.SettingPreferences
import com.tatsuki.fireframe.core.datastore.SettingPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SettingPreferencesModule {

    @Binds
    @Singleton
    fun bind(
        settingPreferencesImpl: SettingPreferencesImpl,
    ): SettingPreferences
}
