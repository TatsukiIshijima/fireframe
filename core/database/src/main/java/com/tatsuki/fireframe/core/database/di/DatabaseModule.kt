package com.tatsuki.fireframe.core.database.di

import android.content.Context
import androidx.room.Room
import com.tatsuki.fireframe.core.database.FireframeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesFireframeDatabase(
        @ApplicationContext context: Context,
    ): FireframeDatabase = Room.databaseBuilder(
        context,
        FireframeDatabase::class.java,
        "fireframe-database",
    ).build()
}