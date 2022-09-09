package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.KorpaItemLayoutBinding
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ProductsItemLayoutBinding
import java.util.function.Consumer

class KorpaViewHolder(
    private val binding: KorpaItemLayoutBinding,
    private val clicked: Consumer<Int>
) : RecyclerView.ViewHolder(binding.root) {

    init {

        binding.button3.setOnClickListener {
            clicked.accept(bindingAdapterPosition)
        }

    }

    fun bind(item: Korpa){

        binding.titleTv.text = item.title
        binding.descriptionTv.text = item.description
        binding.amountTv.text = item.amount.toString()
        binding.priceTv.text = item.price.toString()

    }



}