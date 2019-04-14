package ru.coderedwolf.bookplayer.modules.booksave.select.dialog

import com.xwray.groupie.Section


class SelectFileListSection : Section() {

    private val mItemMap = mutableMapOf<Long, SelectFileItem>()

    fun addItem(item: SelectFileItem) {
        mItemMap[item.audioData.id] = item
        super.add(item)
    }

    fun addAll(list: List<SelectFileItem>) {
        mItemMap.putAll(list.map { it.audioData.id to it })
        super.addAll(list)
    }

    fun updateList(list: Collection<SelectFileItem>) {
        mItemMap.clear()
        mItemMap.putAll(list.map { it.audioData.id to it })
        super.update(list)
    }

    fun setChecked(audioId: Long) {
        val fileItem = mItemMap[audioId] ?: return
        fileItem.isChecked = true
        fileItem.notifyChanged(CHECKED)
    }

    fun setChecked(audioIds: List<Long>) {
        audioIds.forEach {
            val fileItem = mItemMap[it] ?: return@forEach
            fileItem.isChecked = true
            fileItem.notifyChanged(CHECKED)
        }
    }
}