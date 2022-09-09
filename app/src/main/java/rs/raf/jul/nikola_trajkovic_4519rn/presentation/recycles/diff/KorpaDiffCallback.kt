package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa

class KorpaDiffCallback: DiffUtil.ItemCallback<Korpa>() {
    override fun areItemsTheSame(oldItem: Korpa, newItem: Korpa): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Korpa, newItem: Korpa): Boolean {
        return oldItem.title == newItem.title
    }
}