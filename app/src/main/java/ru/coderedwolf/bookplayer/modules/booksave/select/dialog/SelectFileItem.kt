package ru.coderedwolf.bookplayer.modules.booksave.select.dialog

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_select_file_list.view.*
import org.jetbrains.anko.onCheckedChange
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.AudioData

const val CHECKED = "CHECKED"

class SelectFileItem(val audioData: AudioData,
                     val onCheck: (SelectFileItem, Boolean) -> Unit) : Item() {

    var isChecked = false
        set(value) {
            field = value
            notifyChanged(CHECKED)
        }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.checkbox.onCheckedChange { _, isChecked ->
            onCheck.invoke(this@SelectFileItem, isChecked)
        }
        viewHolder.itemView.checkbox.isChecked = isChecked
        viewHolder.itemView.audioFileName.text = audioData.name
        viewHolder.itemView.audioAuthor.text = audioData.name
    }

    override fun bind(viewHolder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        when {
            payloads.contains(CHECKED) -> {
                viewHolder.itemView.checkbox.isChecked = isChecked
            }
            else -> {
                super.bind(viewHolder, position, payloads)
            }
        }
    }

    override fun getLayout() = R.layout.item_select_file_list
}