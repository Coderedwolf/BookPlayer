package ru.coderedwolf.bookplayer.modules.booklist

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.domain.repositories.BookRepository
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class BookListPresenter @Inject constructor(
    private val router: Router,
    private val bookRepository: BookRepository
) : BasePresenter<BookListView>() {

    override fun onViewCreated() {
        launch {
            viewState.showLoading()

            val bookList = async(IO) {
                bookRepository.findAll(10, 0)
            }

            viewState.showBookList(bookList.await())
            viewState.hideLoading()
        }
    }
}