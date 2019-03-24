package ru.coderedwolf.bookplayer.modules.booksave

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_audio_save_book_list.view.*
import org.jetbrains.anko.onClick
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.AudioData

class AudioFileItem(val audioData: AudioData,
                    val onRemove: (AudioFileItem) -> Unit) : Item() {

    override fun getLayout() = R.layout.item_audio_save_book_list

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.fileName.text = audioData.name
        viewHolder.itemView.removeButton.onClick { onRemove(this) }
    }
}