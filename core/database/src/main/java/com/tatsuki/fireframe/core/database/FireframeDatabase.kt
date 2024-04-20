package com.tatsuki.fireframe.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tatsuki.fireframe.core.database.dao.LocalMediaImageDao
import com.tatsuki.fireframe.core.database.model.LocalMediaImageEntity

@Database(
    entities = [
        LocalMediaImageEntity::class,
    ],
    version = 1,
)
internal abstract class FireframeDatabase : RoomDatabase() {
    abstract fun localMediaImageDao(): LocalMediaImageDao
}
