package ru.coderedwolf.bookplayer.modules.filelist

import android.Manifest.permission
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.florent37.runtimepermission.RuntimePermission
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_file_list.*
import org.jetbrains.anko.onClick
import ru.coderedwolf.bookplayer.BookPayerApplication
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.base.MvpAppCompatFragment
import ru.coderedwolf.bookplayer.domain.model.AudioFile
import ru.coderedwolf.bookplayer.modules.common.BackButtonListener

class FileListFragment : MvpAppCompatFragment(), FileListView, BackButtonListener {

    companion object {

        fun newInstance(): Fragment {
            return FileListFragment()
        }
    }

    @InjectPresenter
    lateinit var mFileListPresenter: FileListPresenter

    private val mAudioFileAdapter = GroupAdapter<ViewHolder>()

    @ProvidePresenter
    fun provideBookListPresenter(): FileListPresenter {
        return DaggerFileListComponent.builder()
                .appComponent(BookPayerApplication.component)
                .build()
                .providePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_file_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setTitle(R.string.file_list_fragment_title)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { mFileListPresenter.onBackPressed() }
        permissionButton.onClick { mFileListPresenter.onClickPermissionButton() }
        swipeRefreshLayout.setOnRefreshListener { mFileListPresenter.onRefreshList() }
        bookList.apply {
            setHasFixedSize(true)
            adapter = mAudioFileAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun hideSwipeLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun enableSwipe(enable: Boolean) {
        swipeRefreshLayout.isEnabled = enable
    }

    override fun showPermissionButton(show: Boolean) {
        if (show) {
            layoutPermissionButton.visibility = View.VISIBLE
        } else {
            layoutPermissionButton.visibility = View.GONE
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showFileList(list: List<AudioFile>) {
        mAudioFileAdapter.update(list.map { AudioFileItem(it) })
    }

    override fun requestPermissionStorage() {
        RuntimePermission(requireActivity())
                .request(permission.WRITE_EXTERNAL_STORAGE)
                .onResponse { mFileListPresenter.onPermissionStorageResult(it) }
                .ask()
    }

    override fun onBackPressed(): Boolean {
        mFileListPresenter.onBackPressed()
        return false
    }
}