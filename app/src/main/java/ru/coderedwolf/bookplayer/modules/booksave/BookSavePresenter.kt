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

    private val mFiles = LinkedHashSet<AudioData>()

    fun onViewCreated(audioData: AudioData) {
        mFiles.add(audioData)
        viewState.showAudioInfo(audioData)
    }

    override fun attachView(view: BookSaveView?) {
        super.attachView(view)
        viewState.addFiles(mFiles.toList())
    }

    override fun onBackPressed() {
        router.exit()
    }

    fun onClickAddFileButton() {
        viewState.showSelectFileDialog(mFiles.map { it.id }.toSet())
    }

    fun onClickCancelButton() {
        router.exit()
    }

    fun onClickSaveButton() {

    }

    fun onClickRemoveFile(audioFileItem: AudioFileItem) {
        viewState.removeFile(audioFileItem)
    }

    fun onClickChangeOrderButton() {

    }

    fun onFileSelected(files: List<AudioData>) {
        this.mFiles.addAll(files)
        viewState.addFiles(files)
    }
}
