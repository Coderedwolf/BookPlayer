package ru.coderedwolf.bookplayer.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.coderedwolf.bookplayer.domain.model.Book
import ru.coderedwolf.bookplayer.domain.model.BookFile
import ru.coderedwolf.bookplayer.domain.repositories.BookRepository

@Database(entities = [Book::class, BookFile::class], version = 1)
abstract class BookPlayerDataBase : RoomDatabase() {

    abstract fun bookRepository(): BookRepository
}
