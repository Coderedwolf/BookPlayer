package ru.coderedwolf.bookplayer.di

import dagger.Component
import ru.coderedwolf.bookplayer.domain.repositories.BookRepository
import ru.coderedwolf.bookplayer.managers.AudioFileManager
import ru.coderedwolf.bookplayer.managers.PermissionManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class, NavigationModule::class])
interface AppComponent {

    fun bookRepository(): BookRepository

    fun audioFileManager(): AudioFileManager
    fun permissionManager(): PermissionManager

    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
}