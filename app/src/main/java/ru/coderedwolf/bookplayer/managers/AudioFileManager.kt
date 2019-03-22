package ru.coderedwolf.bookplayer.managers

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import ru.coderedwolf.bookplayer.domain.model.AudioFile
import ru.coderedwolf.bookplayer.extensions.toList


interface AudioFileManager {

    suspend fun findAll(): List<AudioFile>
}

class AudioFileManagerImpl(private val context: Context) : AudioFileManager {

    companion object {

        private const val CONTENT_PATH_ALBUM_ART = "content://media/external/audio/albumart"
    }

    @SuppressLint("Recycle")
    override suspend fun findAll(): List<AudioFile> {

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        )

        val queryCursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null
        )

        val albumArtUri = Uri.parse(CONTENT_PATH_ALBUM_ART)

        return queryCursor.toList { cursor ->
            val albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
            val imageFileUri = ContentUris.withAppendedId(albumArtUri, albumId)

            return@toList AudioFile(
                    id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                    name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                    author = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                    duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)),
                    filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),
                    imageUri = imageFileUri
            )
        }
    }
}

