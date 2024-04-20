package com.tatsuki.fireframe.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tatsuki.fireframe.core.database.model.LocalMediaImageEntity

@Dao
interface LocalMediaImageDao {
    @Query(
        value = """
            SELECT * FROM local_media_image
        """,
    )
    suspend fun getLocalMediaImages(): List<LocalMediaImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalMediaImage(vararg localMediaImage: LocalMediaImageEntity)

    @Query(
        value = """
            DELETE FROM local_media_image
        """,
    )
    suspend fun deleteLocalMediaImages()
}
