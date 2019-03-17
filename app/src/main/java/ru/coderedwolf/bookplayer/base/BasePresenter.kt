package ru.coderedwolf.bookplayer.base

import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ru.iway.iwayapp.base.BaseView
import kotlin.coroutines.CoroutineContext

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 *
 * @param V the type of the View the presenter is based on
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<V : BaseView> : MvpPresenter<V>(), CoroutineScope {

    private val mJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mJob

    protected val IO = Dispatchers.IO + mJob

    final override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onViewCreated()
    }

    final override fun onDestroy() {
        super.onDestroy()
        onViewDestroyed()
        mJob.cancel()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated() {}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed() {}

    /**
     * Optimal method should be call, onBackPressed on Activity or Fragment
     */
    open fun onBackPressed() {}
}