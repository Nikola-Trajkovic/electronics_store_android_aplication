package rs.raf.jul.nikola_trajkovic_4519rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Products(

    val id: Long,
    val title: String,
    val description: String,
    val price: Integer,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Integer,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,

)
