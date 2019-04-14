package ru.coderedwolf.bookplayer.modules.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_book_list.*
import org.jetbrains.anko.onClick
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.Book
import ru.coderedwolf.bookplayer.modules.base.BaseMvpFragment

class BookListFragment : BaseMvpFragment<BookListPresenter>(), BookListView {

    companion object {

        fun newInstance(): Fragment {
            return BookListFragment()
        }
    }

    @InjectPresenter lateinit var mBookListPresenter: BookListPresenter
    @ProvidePresenter fun presenter() = providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddBook.onClick { mBookListPresenter.onClickAddBook() }
    }

    override fun showBookList(list: List<Book>) {

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showEmptyLabel(show: Boolean) {
        if (show) {
            labelEmpty.visibility = View.VISIBLE
        } else {
            labelEmpty.visibility = View.GONE
        }
    }
}