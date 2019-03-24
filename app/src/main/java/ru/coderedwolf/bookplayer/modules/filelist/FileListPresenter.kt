package ru.coderedwolf.bookplayer.modules.filelist

import com.arellomobile.mvp.InjectViewState
import com.github.florent37.runtimepermission.PermissionResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.coderedwolf.bookplayer.base.BasePresenter
import ru.coderedwolf.bookplayer.managers.AudioFileManager
import ru.coderedwolf.bookplayer.managers.PermissionManager
import ru.coderedwolf.bookplayer.modules.SettingScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class FileListPresenter @Inject constructor(
        private val router: Router,
        private val audioFileManager: AudioFileManager,
        private val permissionManager: PermissionManager
) : BasePresenter<FileListView>() {

    companion object {

        private const val DELAY_HIDE = 1000L
    }

    private var mGoToSetting = false

    override fun onViewCreated() {
        if (permissionManager.hasStoragePermission) {
            viewState.showPermissionButton(false)
            updateAudioList()
        } else {
            viewState.enableSwipe(false)
            viewState.showPermissionButton(true)
        }
    }

    override fun attachView(view: FileListView?) {
        super.attachView(view)
        if (mGoToSetting && permissionManager.hasStoragePermission) {
            this.mGoToSetting = false
            viewState.enableSwipe(true)
            viewState.showPermissionButton(false)
            updateAudioList()
        }
    }

    override fun onBackPressed() {
        router.exit()
    }

    fun onPermissionStorageResult(result: PermissionResult) {
        when {
            result.isAccepted -> {
                viewState.enableSwipe(true)
                viewState.showPermissionButton(false)
                updateAudioList()
            }
            result.hasForeverDenied() -> {
                this.mGoToSetting = true
                router.navigateTo(SettingScreen)
            }
        }
    }

    private fun updateAudioList() {
        launch {
            viewState.showLoading()
            val list = withContext(IO) { audioFileManager.findAll() }
            viewState.showFileList(list)
            delay(DELAY_HIDE)
            viewState.hideLoading()
        }
    }

    fun onClickPermissionButton() {
        viewState.requestPermissionStorage()
    }

    fun onRefreshList() {
        launch {
            val list = withContext(IO) { audioFileManager.findAll() }
            viewState.showFileList(list)
            delay(DELAY_HIDE)
            viewState.hideSwipeLoading()
        }
    }

    fun onClickFile(item: AudioFileItem) {

    }
}