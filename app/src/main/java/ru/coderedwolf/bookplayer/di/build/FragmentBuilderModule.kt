package ru.coderedwolf.bookplayer.di.build

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.coderedwolf.bookplayer.modules.booklist.BookListFragment
import ru.coderedwolf.bookplayer.modules.booksave.BookSaveFragment
import ru.coderedwolf.bookplayer.modules.booksave.select.dialog.SelectFileDialog
import ru.coderedwolf.bookplayer.modules.filelist.FileListFragment

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector fun contributeBookListFragment(): BookListFragment
    @ContributesAndroidInjector fun contributeSelectFileDialog(): SelectFileDialog
    @ContributesAndroidInjector fun contributeBookSaveFragment(): BookSaveFragment
    @ContributesAndroidInjector fun contributeFileListFragment(): FileListFragment
}