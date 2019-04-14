package ru.coderedwolf.bookplayer.modules.booksave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.groupiex.plusAssign
import kotlinx.android.synthetic.main.fragment_book_save.*
import org.jetbrains.anko.onClick
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.coderedwolf.bookplayer.extensions.args
import ru.coderedwolf.bookplayer.modules.base.BaseMvpFragment
import ru.coderedwolf.bookplayer.modules.booksave.select.dialog.SelectFileDialog
import ru.coderedwolf.bookplayer.modules.common.BackButtonListener

class BookSaveFragment : BaseMvpFragment<BookSavePresenter>(), BookSaveView,
        BackButtonListener, SelectFileDialog.OnFileSelected {

    companion object {

        private const val SELECT_FILE_DIALOG_TAG = "SELECT_FILE_DIALOG_TAG"

        fun newInstance(audioData: AudioData): Fragment {
            return BookSaveFragment().apply {
                this.mAudioData = audioData
            }
        }
    }

    @InjectPresenter lateinit var mBookSavePresenter: BookSavePresenter
    @ProvidePresenter fun presenter() = providePresenter()

    private val mMainListSection = Section().apply {
        setFooter(FooterItem())
    }

    private val mFileListAdapter: GroupAdapter<ViewHolder> by lazy {
        val adapter = GroupAdapter<ViewHolder>()
        adapter += mMainListSection
        return@lazy adapter
    }

    private var mAudioData: AudioData by args()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        fileList.apply {
            adapter = mFileListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        buttonChangeOrder.onClick { mBookSavePresenter.onClickChangeOrderButton() }
        buttonAddFile.onClick { mBookSavePresenter.onClickAddFileButton() }
        buttonSave.onClick { mBookSavePresenter.onClickSaveButton() }
        if (savedInstanceState == null) {
            mBookSavePresenter.onViewCreated(mAudioData)
        }
    }

    override fun onBackPressed(): Boolean {
        mBookSavePresenter.onBackPressed()
        return false
    }

    override fun showSelectFileDialog(excludeIds: Set<Long>) {
        SelectFileDialog.newInstance(excludeIds)
                .show(childFragmentManager, SELECT_FILE_DIALOG_TAG)
    }

    override fun addFiles(list: List<AudioData>) {
        mMainListSection.addAll(list.map { mapToItem(it) })
    }

    override fun removeFile(item: AudioFileItem) {
        mMainListSection.remove(item)
    }

    override fun showAudioInfo(audioData: AudioData) {
        editBookName.setText(audioData.name)
        editBookAuthor.setText(audioData.author)
    }

    override fun onFileSelected(list: List<AudioData>) {
        mBookSavePresenter.onFileSelected(list)
    }

    private fun initToolbar() {
        toolbar.setTitle(R.string.book_save_fragment_title)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { mBookSavePresenter.onBackPressed() }
    }

    private fun mapToItem(audioData: AudioData): AudioFileItem {
        return AudioFileItem(audioData)
    }
}