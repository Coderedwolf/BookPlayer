package ru.coderedwolf.bookplayer.modules.main

import dagger.Component
import dagger.Module
import ru.coderedwolf.bookplayer.di.AppComponent
import ru.iway.iwayapp.di.scope.LifeTimeScope

@LifeTimeScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun providePresenter(): MainPresenter
}

@Module
interface MainModule {

    @LifeTimeScope
    fun mainPresentor(mainPresenter: MainPresenter): MainPresenter
}