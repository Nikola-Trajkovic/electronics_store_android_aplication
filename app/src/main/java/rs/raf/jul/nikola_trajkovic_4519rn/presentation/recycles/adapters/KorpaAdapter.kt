package rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.KorpaItemLayoutBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.diff.KorpaDiffCallback
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.viewholder.KorpaViewHolder
import java.util.function.Consumer

class KorpaAdapter(
    private val clicked: Consumer<Korpa>
) : ListAdapter<Korpa, KorpaViewHolder>(KorpaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KorpaViewHolder {
        val binding = KorpaItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KorpaViewHolder(binding,
        ) { position: Int? ->
            clicked?.accept(
                getItem(
                    position!!
                )
            )
        }
    }

    override fun onBindViewHolder(holder: KorpaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}