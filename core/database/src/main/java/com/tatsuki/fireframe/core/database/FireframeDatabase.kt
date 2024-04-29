package com.tatsuki.fireframe.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tatsuki.fireframe.core.database.dao.LocalMediaImageDao
import com.tatsuki.fireframe.core.database.dao.SlideGroupDao
import com.tatsuki.fireframe.core.database.model.LocalMediaImageEntity
import com.tatsuki.fireframe.core.database.model.SlideGroupEntity

@Database(
    entities = [
        LocalMediaImageEntity::class,
        SlideGroupEntity::class,
    ],
    version = 1,
)
internal abstract class FireframeDatabase : RoomDatabase() {
    abstract fun localMediaImageDao(): LocalMediaImageDao

    abstract fun slideGroupDao(): SlideGroupDao
}
