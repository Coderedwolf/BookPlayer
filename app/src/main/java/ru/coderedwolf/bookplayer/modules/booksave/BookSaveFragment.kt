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
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_book_save.*
import org.jetbrains.anko.onClick
import ru.coderedwolf.bookplayer.BookPayerApplication
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.base.MvpAppCompatFragment
import ru.coderedwolf.bookplayer.domain.model.AudioData
import ru.coderedwolf.bookplayer.extensions.args

class BookSaveFragment : MvpAppCompatFragment(), BookSaveView {

    companion object {

        fun newInstance(audioData: AudioData): Fragment {
            return BookSaveFragment().apply {
                this.mAudioData = audioData
            }
        }
    }

    @InjectPresenter
    lateinit var mBookSavePresenter: BookSavePresenter

    private val mFileListAdapter = GroupAdapter<ViewHolder>()

    @ProvidePresenter
    fun provideBookListPresenter(): BookSavePresenter {
        return DaggerBookSaveComponent.builder()
                .appComponent(BookPayerApplication.component)
                .build()
                .providePresenter()
    }

    private var mAudioData: AudioData by args()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        fileList.apply {
            setHasFixedSize(true)
            adapter = mFileListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        buttonAddFile.onClick { mBookSavePresenter.onClickAddFileButton() }
        buttonCancel.onClick { mBookSavePresenter.onClickCancelButton() }
        buttonSave.onClick { mBookSavePresenter.onClickSaveButton() }
        if (savedInstanceState == null) {
            mBookSavePresenter.onViewCreated(mAudioData)
        }
    }

    override fun addFile(item: AudioData) {
        mFileListAdapter.add(mapToItem(item))
    }

    override fun removeFile(item: AudioFileItem) {
        mFileListAdapter.remove(item)
    }

    override fun showAudioInfo(audioData: AudioData) {
        editBookName.setText(audioData.name)
        editBookAuthor.setText(audioData.author)

        mFileListAdapter.add(mapToItem(audioData))
    }

    private fun initToolbar() {
        toolbar.setTitle(R.string.book_save_fragment_title)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { mBookSavePresenter.onBackPressed() }
    }

    private fun mapToItem(audioData: AudioData): AudioFileItem {
        return AudioFileItem(audioData, onRemove = {
            mBookSavePresenter.onClickRemoveFile(it)
        })
    }
}