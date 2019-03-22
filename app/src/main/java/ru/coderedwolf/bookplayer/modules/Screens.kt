package ru.coderedwolf.bookplayer.modules

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment
import ru.coderedwolf.bookplayer.modules.booklist.BookListFragment
import ru.coderedwolf.bookplayer.modules.filelist.FileListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object BookListScreen : SupportAppScreen() {

    override fun getFragment(): Fragment {
        return BookListFragment.newInstance()
    }
}

object FileListScreen : SupportAppScreen() {

    override fun getFragment(): Fragment {
        return FileListFragment.newInstance()
    }
}

object SettingScreen : SupportAppScreen() {

    override fun getActivityIntent(context: Context): Intent {
        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null))
    }
}