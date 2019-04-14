package ru.coderedwolf.bookplayer.modules.booksave.select.dialog

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.coderedwolf.bookplayer.managers.AudioFileRepository
import javax.inject.Inject

@InjectViewState
class SelectFilePresenter @Inject constructor(
        private val audioFileRepository: AudioFileRepository
) : BasePresenter<SelectFileView>() {

    private val mSetFiles = mutableSetOf<AudioData>()

    fun onViewCreated(excludeIds: Set<Long>) {
        launch {
            val list = withContext(IO) {
                audioFileRepository.findAll().filterNot { excludeIds.contains(it.id) }
            }
            viewState.showFileList(list)
        }
    }

    override fun attachView(view: SelectFileView?) {
        super.attachView(view)
        viewState.setCheckedFiles(mSetFiles.map { it.id })
    }

    fun onClickCancel() {
        viewState.dismiss()
    }

    fun onAddAudioFile(audioData: AudioData) {
        mSetFiles.add(audioData)
    }

    fun onRemoveAudioFile(audioData: AudioData) {
        mSetFiles.remove(audioData)
    }

    fun onClickConfirm() {
        viewState.setSelectedFiles(mSetFiles.toList())
        viewState.dismiss()
    }
}