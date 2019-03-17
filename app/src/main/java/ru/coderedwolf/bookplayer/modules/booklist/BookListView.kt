package ru.coderedwolf.bookplayer.modules.booklist

import ru.coderedwolf.bookplayer.domain.model.Book
import ru.iway.iwayapp.base.BaseView
import ru.iway.iwayapp.base.LoadingView

interface BookListView : BaseView, LoadingView {

    fun showBookList(list: List<Book>)
}