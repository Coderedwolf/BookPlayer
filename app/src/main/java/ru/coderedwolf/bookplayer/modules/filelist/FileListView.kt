package ru.coderedwolf.bookplayer.modules.filelist

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.coderedwolf.bookplayer.base.LoadingView
import ru.coderedwolf.bookplayer.domain.model.AudioFile
import ru.iway.iwayapp.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface FileListView : BaseView, LoadingView {

    fun showFileList(list: List<AudioFile>)
    fun showPermissionButton(show: Boolean)
    fun hideSwipeLoading()
    fun enableSwipe(enable: Boolean)

    @StateStrategyType(SkipStrategy::class) fun requestPermissionStorage()
}