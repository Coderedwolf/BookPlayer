package ru.coderedwolf.bookplayer.extensions

import android.database.Cursor

inline fun <T> Cursor?.toList(block: (Cursor) -> T): List<T> {
    return this.use { cursor ->
        if (cursor == null) {
            return@use emptyList()
        }
        return@use mutableListOf<T>().also {
            if (cursor.moveToFirst()) {
                do {
                    it.add(block.invoke(cursor))
                } while (cursor.moveToNext())
            }
        }
    }
}