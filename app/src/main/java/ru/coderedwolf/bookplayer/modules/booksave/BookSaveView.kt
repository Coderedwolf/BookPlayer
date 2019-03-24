package ru.coderedwolf.bookplayer.modules.booksave

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.iway.iwayapp.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface BookSaveView : BaseView {

    fun showAudioInfo(audioData: AudioData)

    @StateStrategyType(OneExecutionStateStrategy::class) fun addFile(item: AudioData)
    @StateStrategyType(OneExecutionStateStrategy::class) fun removeFile(item: AudioFileItem)
}