package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ImageItemLayoutBinding
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ProductsItemLayoutBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.diff.ImageDiffCallback
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder.ImageViewHolder
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder.ProductsViewHolder

class ImageAdapter : ListAdapter<String, ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}