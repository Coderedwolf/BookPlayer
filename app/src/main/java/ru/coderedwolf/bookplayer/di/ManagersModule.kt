package ru.coderedwolf.bookplayer.di

import dagger.Binds
import dagger.Module
import ru.coderedwolf.bookplayer.managers.PermissionManager
import ru.coderedwolf.bookplayer.managers.PermissionManagerImpl
import javax.inject.Singleton

@Module
interface ManagersModule {

    @Singleton
    @Binds
    fun permissionManager(impl: PermissionManagerImpl): PermissionManager
}