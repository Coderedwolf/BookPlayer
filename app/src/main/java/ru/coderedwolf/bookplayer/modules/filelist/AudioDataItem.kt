package ru.coderedwolf.bookplayer.modules.filelist

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_file_list.view.*
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.domain.model.AudioData

class AudioDataItem(val audioData: AudioData) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView)
                .load(audioData.imageUri)
                .into(viewHolder.itemView.audioImage)
        val durationMin = audioData.duration / 60_000
        viewHolder.itemView.audioDuration.text = viewHolder.itemView.context.getString(R.string.format_minutes, durationMin)
        viewHolder.itemView.audioFileName.text = audioData.name
        viewHolder.itemView.audioAuthor.text = audioData.author
    }

    override fun getLayout() = R.layout.item_file_list

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other?.layout != layout) {
            return false
        }
        other as AudioDataItem
        return other.audioData.id == audioData.id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioDataItem

        if (audioData.id == other.id && audioData.author == other.audioData.author
                && audioData.filePath == other.audioData.filePath) {
            return true
        }

        return false
    }

    override fun hashCode(): Int {
        return audioData.hashCode()
    }
}