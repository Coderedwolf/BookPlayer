package ru.coderedwolf.bookplayer.base

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoadingView {

    /**
     * Show loading of view
     */
    @StateStrategyType(OneExecutionStateStrategy::class) fun showLoading()

    /**
     * Hide loading on view
     */
    @StateStrategyType(OneExecutionStateStrategy::class) fun hideLoading()
}