package ru.coderedwolf.bookplayer.modules.main

import com.arellomobile.mvp.InjectViewState
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.iway.iwayapp.di.scope.LifeTimeScope
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
@LifeTimeScope
class MainPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainView>() {

    override fun onViewCreated() {
//        router.replaceScreen()
    }

    override fun onBackPressed() {
        router.finishChain()
    }
}
