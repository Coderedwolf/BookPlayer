package ru.coderedwolf.bookplayer.domain.repositories

import androidx.room.*
import ru.coderedwolf.bookplayer.domain.model.Book

@Dao
interface BookRepository {

    @Query("SELECT * FROM book where id = :id")
    suspend fun findOne(id: Long): Book

    @Query("SELECT * FROM book LIMIT :limit OFFSET :offset")
    suspend fun findAll(limit: Int, offset: Int): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(book: Book)

    @Delete
    suspend fun remove(book: Book)
}