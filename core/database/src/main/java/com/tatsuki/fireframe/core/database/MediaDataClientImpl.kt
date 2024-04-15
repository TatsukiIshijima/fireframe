package com.tatsuki.fireframe.core.database

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.tatsuki.fireframe.core.database.model.ImageDirectoryEntity
import com.tatsuki.fireframe.core.database.model.ImageEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaDataClientImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : MediaDataClient {

    override fun queryAllImageDirectories(): List<ImageDirectoryEntity> {
        val directories = mutableListOf<ImageDirectoryEntity>()
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val query = context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder,
        )
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val directory = ImageDirectoryEntity(
                    id = id,
                    name = name,
                )
                if (!directories.any { it.name == directory.name }) {
                    directories.add(directory)
                }
            }
        }
        query?.close()
        return directories
    }

    override fun queryImagesFromDirectory(name: String): List<ImageEntity> {
        val images = mutableListOf<ImageEntity>()
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
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
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val fileNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val data = cursor.getString(dataColumn)
                val fileName = cursor.getString(fileNameColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id,
                )
                val image = ImageEntity(
                    id = id,
                    path = data,
                    uri = contentUri,
                    name = fileName,
                )
                images.add(image)
            }
        }
        query?.close()
        return images
    }
}
