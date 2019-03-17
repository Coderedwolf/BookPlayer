package ru.coderedwolf.bookplayer.modules.booklist

import dagger.Component
import dagger.Module
import ru.coderedwolf.bookplayer.di.AppComponent
import ru.iway.iwayapp.di.scope.LifeTimeScope

@LifeTimeScope
@Component(modules = [BookListModule::class], dependencies = [AppComponent::class])
interface BookListComponent {

    fun providePresenter(): BookListPresenter
}

@Module
interface BookListModule {

    @LifeTimeScope
    fun mainPresentor(bookListPresenter: BookListPresenter): BookListPresenter
}