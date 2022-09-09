package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products

class ProductsDiffCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.brand == newItem.brand
    }
}