package com.tatsuki.fireframe.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tatsuki.fireframe.core.model.LocalMediaImage

@Entity(
    tableName = "local_media_image",
)
data class LocalMediaImageEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "group_id")
    val groupId: Long,
) {
    companion object {
        fun from(
            localMediaImage: LocalMediaImage,
            groupId: Long,
        ) = LocalMediaImageEntity(
            id = localMediaImage.id,
            groupId = groupId,
        )
    }
}

fun LocalMediaImageEntity.asExternalModel() = LocalMediaImage(
    id = id,
)
