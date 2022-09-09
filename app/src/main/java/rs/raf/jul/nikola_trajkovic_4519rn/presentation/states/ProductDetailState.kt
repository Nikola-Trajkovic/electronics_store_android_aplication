package rs.raf.jul.nikola_trajkovic_4519rn.presentation.states

import rs.raf.jul.nikola_trajkovic_4519rn.data.models.ProductsResponse

sealed class ProductDetailState {

    data class Success(val product: List<String>): ProductDetailState()
    data class Error(val message: String): ProductDetailState()

}