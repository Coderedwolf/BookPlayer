package ru.coderedwolf.bookplayer.modules.booklist

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.coderedwolf.bookplayer.BookPayerApplication
import ru.coderedwolf.bookplayer.base.MvpAppCompatFragment
import ru.coderedwolf.bookplayer.domain.model.Book

class BookListFragment : MvpAppCompatFragment(), BookListView {

    @InjectPresenter
    lateinit var mBookListPresenter: BookListPresenter

    @ProvidePresenter
    fun provideBookListPresenter(): BookListPresenter {
        return DaggerBookListComponent.builder()
            .appComponent(BookPayerApplication.component)
            .build()
            .providePresenter()
    }

    override fun showBookList(list: List<Book>) {

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}