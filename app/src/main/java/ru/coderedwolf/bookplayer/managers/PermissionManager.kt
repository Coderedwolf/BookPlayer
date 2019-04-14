package ru.coderedwolf.bookplayer.managers

import android.Manifest
import android.content.Context
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import javax.inject.Inject

interface PermissionManager {

    val hasStoragePermission: Boolean
}

class PermissionManagerImpl @Inject constructor(
        private val context: Context
) : PermissionManager {

    override val hasStoragePermission: Boolean
        get() = checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED
}