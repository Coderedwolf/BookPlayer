package ru.coderedwolf.bookplayer.modules.main

import com.arellomobile.mvp.InjectViewState
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.modules.BookListScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainView>() {

    override fun onViewCreated() {
        router.replaceScreen(BookListScreen)
    }

    override fun onBackPressed() {
        router.finishChain()
    }
}
