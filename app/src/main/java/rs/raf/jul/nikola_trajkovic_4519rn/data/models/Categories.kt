package rs.raf.jul.nikola_trajkovic_4519rn.data.models


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Categories(

    val kategorije: List<String>

)
