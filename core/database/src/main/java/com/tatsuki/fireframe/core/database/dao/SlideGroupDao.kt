package com.tatsuki.fireframe.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tatsuki.fireframe.core.database.model.SlideGroupEntity

@Dao
interface SlideGroupDao {

    @Query(
        value = """
            SELECT * FROM slide_group
        """,
    )
    suspend fun getSlideGroups(): List<SlideGroupEntity>

    @Query(
        value = """
            SELECT * FROM slide_group WHERE id = :id
        """,
    )
    suspend fun getSlideGroup(id: Long): SlideGroupEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSlideGroup(vararg slideGroup: SlideGroupEntity): List<Long>

    @Query(
        value = """
            DELETE FROM slide_group WHERE id = :id
        """,
    )
    suspend fun deleteSlideGroup(id: Long)
}
