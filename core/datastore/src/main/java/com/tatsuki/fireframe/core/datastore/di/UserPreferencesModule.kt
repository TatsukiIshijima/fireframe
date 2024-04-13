package com.tatsuki.fireframe.core.datastore.di

import com.tatsuki.fireframe.core.datastore.UserPreferences
import com.tatsuki.fireframe.core.datastore.UserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserPreferencesModule {

    @Binds
    @Singleton
    fun bindUserPreference(
        userPreferencesImpl: UserPreferencesImpl,
    ): UserPreferences
}
