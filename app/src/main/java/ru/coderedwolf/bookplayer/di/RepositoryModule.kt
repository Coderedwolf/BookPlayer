package ru.coderedwolf.bookplayer.di

import dagger.Binds
import dagger.Module
import ru.coderedwolf.bookplayer.managers.AudioFileRepository
import ru.coderedwolf.bookplayer.managers.AudioFileRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindAudioFileManager(impl: AudioFileRepositoryImpl): AudioFileRepository
}