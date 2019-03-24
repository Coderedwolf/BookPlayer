package ru.coderedwolf.bookplayer.modules.filelist

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.coderedwolf.bookplayer.base.LoadingView
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.iway.iwayapp.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface FileListView : BaseView, LoadingView {

    fun showFileList(list: List<AudioData>)
    fun showPermissionButton(show: Boolean)

    fun enableSwipe(enable: Boolean)
    fun hideSwipeLoading()

    @StateStrategyType(SkipStrategy::class) fun requestPermissionStorage()
}