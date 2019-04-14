package ru.coderedwolf.bookplayer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.coderedwolf.bookplayer.domain.BookPlayerDataBase
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun bookPlayerDataBase(context: Context): BookPlayerDataBase {
        return Room.databaseBuilder(context, BookPlayerDataBase::class.java, "BookPlayerDataBase")
                .build()
    }

    @Provides
    @Singleton
    fun bookRepository(bookPlayerDataBase: BookPlayerDataBase) = bookPlayerDataBase.bookRepository()
}