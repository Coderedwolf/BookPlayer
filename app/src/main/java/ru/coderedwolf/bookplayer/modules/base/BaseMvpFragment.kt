package ru.coderedwolf.bookplayer.modules.base

import android.content.Context
import dagger.android.support.AndroidSupportInjection
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.base.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseMvpFragment<P : BasePresenter<*>> : MvpAppCompatFragment() {

    @Inject lateinit var mPresenterProvider: Provider<P>

    fun providePresenter(): P = mPresenterProvider.get()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}