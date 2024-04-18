package com.tatsuki.fireframe.core.database.di

import com.tatsuki.fireframe.core.database.MediaDataClient
import com.tatsuki.fireframe.core.database.MediaDataClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediaDataClientModule {

    @Binds
    @Singleton
    fun bindMediaDataClient(
        mediaDataClientImpl: MediaDataClientImpl,
    ): MediaDataClient
}
