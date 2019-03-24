package ru.coderedwolf.bookplayer.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AudioData(
        val id: Long,
        val name: String,
        val author: String,
        val duration: Long,
        val filePath: String,
        val imageUri: String
) : Parcelable
