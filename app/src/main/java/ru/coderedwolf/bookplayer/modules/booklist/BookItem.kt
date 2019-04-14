package ru.coderedwolf.bookplayer.modules.booklist

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.Book

class BookItem(val book: Book) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout() = R.layout.item_book_list
}