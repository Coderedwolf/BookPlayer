package ru.coderedwolf.bookplayer.di

import dagger.Component
import ru.coderedwolf.bookplayer.domain.repositories.BookRepository
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class, NavigationModule::class])
interface AppComponent {

    fun bookRepository(): BookRepository

    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
}