package ru.coderedwolf.bookplayer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val fileId: Long,
    val progress: Int,
    val lastPoint: Long
)