package ru.coderedwolf.bookplayer.di


import android.content.Context
import dagger.Module
import dagger.Provides
import ru.coderedwolf.bookplayer.managers.AudioFileManager
import ru.coderedwolf.bookplayer.managers.AudioFileManagerImpl
import ru.coderedwolf.bookplayer.managers.PermissionManager
import ru.coderedwolf.bookplayer.managers.PermissionManagerImpl
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun contextApplication(): Context {
        return context
    }

    @Provides
    @Singleton
    fun audioFileManager(context: Context): AudioFileManager {
        return AudioFileManagerImpl(context)
    }

    @Provides
    @Singleton
    fun permissionManager(context: Context): PermissionManager {
        return PermissionManagerImpl(context)
    }
}