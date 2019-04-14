package ru.coderedwolf.bookplayer.modules.booklist

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.domain.repositories.BookRepository
import ru.coderedwolf.bookplayer.modules.FileListScreen
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

            val bookList = withContext(IO) { bookRepository.findAll(10, 0) }
            if (bookList.isEmpty()) {
                viewState.showEmptyLabel(true)
            } else {
                viewState.showBookList(bookList)
            }
            viewState.hideLoading()
        }
    }

    fun onClickAddBook() {
        router.navigateTo(FileListScreen)
    }
}