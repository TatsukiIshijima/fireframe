package com.tatsuki.fireframe.core.data.di

import com.tatsuki.fireframe.core.data.repository.MediaRepository
import com.tatsuki.fireframe.core.data.repository.MediaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediaRepositoryModule {

    @Binds
    @Singleton
    fun bindMediaRepository(
        mediaRepositoryImpl: MediaRepositoryImpl,
    ): MediaRepository
}
