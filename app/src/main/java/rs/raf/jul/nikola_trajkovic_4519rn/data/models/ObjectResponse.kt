package rs.raf.jul.nikola_trajkovic_4519rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ObjectResponse(
    val products: List<Products>,
    val total: Integer,
    val skip: Integer,
    val limit: Integer
)
