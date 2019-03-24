package ru.coderedwolf.bookplayer.modules.filelist

import dagger.Component
import dagger.Module
import ru.coderedwolf.bookplayer.di.AppComponent
import ru.iway.iwayapp.di.scope.LifeTimeScope

@LifeTimeScope
@Component(modules = [FileListModule::class], dependencies = [AppComponent::class])
interface FileListComponent {

    fun providePresenter(): FileListPresenter
}

@Module
interface FileListModule {

    @LifeTimeScope
    fun fileListPresenter(fileListPresenter: FileListPresenter): FileListPresenter
}