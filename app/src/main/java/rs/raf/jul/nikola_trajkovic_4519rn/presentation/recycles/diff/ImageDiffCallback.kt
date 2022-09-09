package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.diff

import androidx.recyclerview.widget.DiffUtil

class ImageDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.length == newItem.length
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}