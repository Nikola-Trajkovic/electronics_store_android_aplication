package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ImageItemLayoutBinding
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ProductsItemLayoutBinding

class ImageViewHolder(
    private val binding: ImageItemLayoutBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind (image: String){

        Glide
            .with(context)
            .load(image)
            .override(500, 500)
            .into(binding.img)

    }

}