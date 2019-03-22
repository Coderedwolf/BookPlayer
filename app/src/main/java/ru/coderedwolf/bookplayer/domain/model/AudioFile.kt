package ru.coderedwolf.bookplayer.domain.model

import android.net.Uri

data class AudioFile(
        val id: Long,
        val name: String,
        val author: String,
        val duration: Long,
        val filePath: String,
        val imageUri: Uri
)
