package com.tatsuki.fireframe.core.database

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.core.database.getStringOrNull
import com.tatsuki.fireframe.core.model.MediaImage
import com.tatsuki.fireframe.core.model.MediaImageDirectory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaDataClientImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : MediaDataClient {

    override suspend fun queryAllImageDirectories(): List<MediaImageDirectory> {
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.VOLUME_NAME,
            MediaStore.Images.Media.DATA,
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val query = context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder,
        )
        try {
            val directories = mutableListOf<MediaImageDirectory>()
            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                val displayNameColumn =
                    cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getStringOrNull(displayNameColumn) ?: ""
                    if (displayName.isEmpty()) {
                        continue
                    }
                    val directory = MediaImageDirectory(
                        id = id,
                        name = displayName,
                    )
                    if (directories.any { it.name == directory.name }) {
                        continue
                    }
                    Log.d("MediaDataClientImpl", "id: $id, displayName: $displayName")
                    directories.add(directory)
                }
            }
            return directories
        } catch (e: Exception) {
            throw e
        } finally {
            query?.close()
        }
    }

    override suspend fun queryImagesFromDirectory(name: String): List<MediaImage> {
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val selection = "${MediaStore.Images.Media.DATA} LIKE ?"
        val selectionArgs = arrayOf("%$name%")
        val query = context.contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder,
        )
        try {
            val images = mutableListOf<MediaImage>()
            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val fileNameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val fileName = cursor.getString(fileNameColumn)
                    val image = MediaImage(
                        id = id,
                        name = fileName,
                    )
                    Log.d("MediaDataClientImpl", "id: $id, fileName: $fileName")
                    images.add(image)
                }
            }
            return images
        } catch (e: Exception) {
            throw e
        } finally {
            query?.close()
        }
    }
}
