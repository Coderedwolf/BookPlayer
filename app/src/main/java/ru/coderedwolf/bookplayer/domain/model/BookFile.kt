package ru.coderedwolf.bookplayer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookFile(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val path: String,
    val fileName: String,
    val fileSize: Long
)