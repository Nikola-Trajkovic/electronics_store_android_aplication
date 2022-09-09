package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ProductsItemLayoutBinding
import java.util.function.Consumer

class ProductsViewHolder(

    private val binding: ProductsItemLayoutBinding,
    private val context: Context,
    private val clicked: Consumer<Int>

    ) : RecyclerView.ViewHolder(binding.root) {

    init{
        binding.root.setOnClickListener {
            clicked.accept(bindingAdapterPosition)
        }
    }

    fun bind(products: Products){

        binding.titleTv.text = products.title
        binding.pricaTv.text = products.price.toString()
        binding.raitingTv.text = products.rating.toString()

        if(products.rating < 4.2){
            binding.raitingTv.setBackgroundColor(Color.RED);
        }else if(products.rating < 4.4){
            binding.raitingTv.setBackgroundColor(Color.rgb(255,165,0));
        }else if(products.rating < 4.6){
            binding.raitingTv.setBackgroundColor(Color.YELLOW);
        }else if(products.rating < 4.8){
            binding.raitingTv.setBackgroundColor(Color.rgb(144,238,144));
        }else{
            binding.raitingTv.setBackgroundColor(Color.GREEN);
        }

        Glide
            .with(context)
            .load(products.thumbnail)
            .override(300, 500)
            .into(binding.imageView2)


    }

}