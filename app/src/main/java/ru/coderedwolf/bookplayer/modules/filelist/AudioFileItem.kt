package ru.coderedwolf.bookplayer.modules.filelist

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_file_list.view.*
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.AudioFile

class AudioFileItem(private val audioFile: AudioFile) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView)
                .load(audioFile.imageUri)
                .into(viewHolder.itemView.audioImage)
        viewHolder.itemView.audioFileName.text = audioFile.name
        viewHolder.itemView.audioAuthor.text = audioFile.author
    }

    override fun getLayout() = R.layout.item_file_list

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other?.layout != layout) {
            return false
        }
        other as AudioFileItem
        return other.audioFile.id == audioFile.id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioFileItem

        if (audioFile.id == other.id && audioFile.author == other.audioFile.author
                && audioFile.filePath == other.audioFile.filePath) {
            return true
        }

        return false
    }

    override fun hashCode(): Int {
        return audioFile.hashCode()
    }
}