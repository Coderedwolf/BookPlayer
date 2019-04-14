package ru.coderedwolf.bookplayer.modules.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.base.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Provider

abstract class RoutingMvpActivity<P : BasePresenter<*>>
    : MvpAppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var mPresenterProvider: Provider<P>
    @Inject lateinit var mNavigationHolder: NavigatorHolder

    private lateinit var mNavigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mNavigator = getNavigator()
    }

    fun providePresenter(): P = mPresenterProvider.get()

    abstract fun getNavigator(): Navigator

    override fun onResumeFragments() {
        super.onResumeFragments()
        mNavigationHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        super.onPause()
        mNavigationHolder.removeNavigator()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}