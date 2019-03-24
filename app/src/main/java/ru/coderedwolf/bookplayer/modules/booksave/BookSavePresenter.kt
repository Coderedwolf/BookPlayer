package ru.coderedwolf.bookplayer.modules.booksave

import com.arellomobile.mvp.InjectViewState
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class BookSavePresenter @Inject constructor(
        private val router: Router
) : BasePresenter<BookSaveView>() {

    private lateinit var mInitAudioData: AudioData

    fun onViewCreated(audioData: AudioData) {
        this.mInitAudioData = audioData
        viewState.showAudioInfo(audioData)
    }

    override fun onBackPressed() {
        router.exit()
    }

    fun onClickAddFileButton() {

    }

    fun onClickCancelButton() {
        router.exit()
    }

    fun onClickSaveButton() {

    }

    fun onClickRemoveFile(audioFileItem: AudioFileItem) {
        viewState.removeFile(audioFileItem)
    }
}
