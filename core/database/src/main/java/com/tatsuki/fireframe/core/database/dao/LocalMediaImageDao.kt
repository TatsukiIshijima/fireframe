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
            SELECT * FROM local_media_image WHERE group_id = :groupId
        """,
    )
    suspend fun getLocalMediaImagesByGroupId(groupId: Long): List<LocalMediaImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalMediaImages(localMediaImages: List<LocalMediaImageEntity>)

    @Query(
        value = """
            DELETE FROM local_media_image WHERE group_id = :groupId
        """,
    )
    suspend fun deleteLocalMediaImagesByGroupId(groupId: Long)
}
