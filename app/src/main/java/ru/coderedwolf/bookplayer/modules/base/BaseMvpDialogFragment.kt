package ru.coderedwolf.bookplayer.modules.base

import android.content.Context
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.base.MvpDialogFragment
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseMvpDialogFragment<P : BasePresenter<*>> : MvpDialogFragment() {

    @Inject lateinit var mPresenterProvider: Provider<P>

    @ProvidePresenter
    fun providePresenter(): P = mPresenterProvider.get()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}