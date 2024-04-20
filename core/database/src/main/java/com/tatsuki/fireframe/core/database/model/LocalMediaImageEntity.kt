package com.tatsuki.fireframe.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tatsuki.fireframe.core.model.MediaImage

@Entity(
    tableName = "local_media_image",
)
data class LocalMediaImageEntity(
    @PrimaryKey
    val id: Long,
)

fun LocalMediaImageEntity.asExternalModel() = MediaImage(
    id = id,
)

fun MediaImage.toEntity() = LocalMediaImageEntity(
    id = id,
)
