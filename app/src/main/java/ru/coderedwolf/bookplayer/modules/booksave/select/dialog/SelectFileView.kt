package ru.coderedwolf.bookplayer.modules.booksave.select.dialog

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.iway.iwayapp.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface SelectFileView : BaseView {

    fun showFileList(list: List<AudioData>)
    fun setCheckedFiles(fileIds: List<Long>)
    fun setSelectedFiles(list: List<AudioData>)

    fun dismiss()
}