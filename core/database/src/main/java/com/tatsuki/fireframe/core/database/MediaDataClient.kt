package com.tatsuki.fireframe.core.database

import com.tatsuki.fireframe.core.model.LocalMediaDirectory

interface MediaDataClient {

    suspend fun queryAllLocalMediaDirectories(): List<LocalMediaDirectory>
}
