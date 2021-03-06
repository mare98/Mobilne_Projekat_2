package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.marko_vesovic_rn2417.data.models.Note

class NotesDiffItemCallback() : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.title == newItem.title &&
                oldItem.content == newItem.content &&
                oldItem.archived == newItem.archived
    }
}