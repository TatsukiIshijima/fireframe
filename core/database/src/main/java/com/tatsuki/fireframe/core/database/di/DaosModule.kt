package com.tatsuki.fireframe.core.database.di

import com.tatsuki.fireframe.core.database.FireframeDatabase
import com.tatsuki.fireframe.core.database.dao.LocalMediaImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesLocalMediaImageDao(
        database: FireframeDatabase,
    ): LocalMediaImageDao = database.localMediaImageDao()
}
