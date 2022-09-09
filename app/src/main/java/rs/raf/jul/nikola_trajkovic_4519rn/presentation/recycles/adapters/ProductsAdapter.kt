package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ProductsItemLayoutBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.diff.ProductsDiffCallback
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder.ProductsViewHolder
import java.util.function.Consumer

class ProductsAdapter(
    private val clicked: Consumer<Products>
) : ListAdapter<Products, ProductsViewHolder>(ProductsDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ProductsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding, parent.context,
        ) { position: Int? ->
            clicked?.accept(
                getItem(
                    position!!
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}