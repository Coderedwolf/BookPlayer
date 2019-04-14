package ru.coderedwolf.bookplayer.modules.booksave.select.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.groupiex.plusAssign
import kotlinx.android.synthetic.main.dialog_fragment_select_file.*
import ru.coderedwolf.bookplayer.BookPlayerApplication
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.base.MvpDialogFragment
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.coderedwolf.bookplayer.extensions.args


class SelectFileDialog : MvpDialogFragment(), SelectFileView {

    companion object {

        fun newInstance(excludeIds: Set<Long>): DialogFragment {
            return SelectFileDialog().apply {
                this.mExcludeIds = excludeIds
            }
        }
    }

    private var onFileSelected: OnFileSelected? = null

    @ProvidePresenter
    fun provideSelectFilePresenter(): SelectFilePresenter {
        return DaggerSelectFileComponent.builder()
                .appComponent(BookPlayerApplication.component)
                .build()
                .providePresenter()
    }

    @InjectPresenter
    lateinit var mSelectFilePresenter: SelectFilePresenter

    private var mExcludeIds: Set<Long> by args()
    private val mSelectFileSection = SelectFileListSection()

    private val mSelectFileAdapter: GroupAdapter<ViewHolder> by lazy {
        val groupAdapter = GroupAdapter<ViewHolder>()
        groupAdapter += mSelectFileSection
        groupAdapter
    }

    override fun onStart() {
        super.onStart()
        onFileSelected = parentFragment as OnFileSelected
    }

    override fun onStop() {
        super.onStop()
        onFileSelected = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_select_file, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fileList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mSelectFileAdapter
        }
        buttonCancel.onClick { mSelectFilePresenter.onClickCancel() }
        buttonConfirm.onClick { mSelectFilePresenter.onClickConfirm() }
        if (savedInstanceState == null) {
            mSelectFilePresenter.onViewCreated(mExcludeIds)
        }
    }

    override fun showFileList(list: List<AudioData>) {
        mSelectFileSection.updateList(list.map { mapToItem(it) })
    }

    override fun setCheckedFiles(fileIds: List<Long>) {
        mSelectFileSection.setChecked(fileIds)
    }

    override fun setSelectedFiles(list: List<AudioData>) {
        onFileSelected?.onFileSelected(list)
    }

    private fun mapToItem(it: AudioData): SelectFileItem {
        return SelectFileItem(it, onCheck = { item, isChecked ->
            if (isChecked) {
                mSelectFilePresenter.onAddAudioFile(item.audioData)
            } else {
                mSelectFilePresenter.onRemoveAudioFile(item.audioData)
            }
        })
    }

    interface OnFileSelected {

        fun onFileSelected(list: List<AudioData>)
    }
}