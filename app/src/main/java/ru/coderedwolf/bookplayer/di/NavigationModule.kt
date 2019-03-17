package ru.coderedwolf.bookplayer.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter() = cicerone.router as Router

    @Provides
    @Singleton
    fun provideNavigatorHolder() = cicerone.navigatorHolder!!
}