package ru.coderedwolf.bookplayer.modules.booksave

import dagger.Component
import dagger.Module
import ru.coderedwolf.bookplayer.di.AppComponent
import ru.iway.iwayapp.di.scope.LifeTimeScope

@LifeTimeScope
@Component(modules = [BookSaveModule::class], dependencies = [AppComponent::class])
interface BookSaveComponent {

    fun providePresenter(): BookSavePresenter
}

@Module
interface BookSaveModule {

    @LifeTimeScope
    fun bookSavePresenter(bookSavePresenter: BookSavePresenter): BookSavePresenter
}