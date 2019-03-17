package ru.coderedwolf.bookplayer.modules.main

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.coderedwolf.bookplayer.BookPayerApplication
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.modules.common.BackButtonListener
import ru.coderedwolf.bookplayer.modules.common.RoutingMvpActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : RoutingMvpActivity(), MainView {

    private val mNavigator = SupportAppNavigator(this, supportFragmentManager, R.id.fragmentContainer)

    override fun getNavigator() = mNavigator

    override fun getNavigationHolder() = BookPayerApplication.component.navigatorHolder()

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter {
        return DaggerMainComponent.builder()
            .appComponent(BookPayerApplication.component)
            .build()
            .providePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        val fragment = findNowFragment()
        if (fragment is BackButtonListener) {
            if (fragment.onBackPressed()) {
                mMainPresenter.onBackPressed()
            }
        } else {
            mMainPresenter.onBackPressed()
        }
    }

    private fun findNowFragment() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
}