package ru.coderedwolf.bookplayer.modules.booklist

import ru.coderedwolf.bookplayer.base.LoadingView
import ru.coderedwolf.bookplayer.domain.model.Book
import ru.iway.iwayapp.base.BaseView

interface BookListView : BaseView, LoadingView {

    fun showBookList(list: List<Book>)
    fun showEmptyLabel(show: Boolean)
}