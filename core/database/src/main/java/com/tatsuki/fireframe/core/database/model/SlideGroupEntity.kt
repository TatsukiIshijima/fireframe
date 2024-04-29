package com.tatsuki.fireframe.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tatsuki.fireframe.core.model.SlideGroup

@Entity(
    tableName = "slide_group",
)
data class SlideGroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "group_name")
    val groupName: String,
)

fun SlideGroupEntity.asExternalModel() = SlideGroup(
    id = id,
    groupName = groupName,
)
