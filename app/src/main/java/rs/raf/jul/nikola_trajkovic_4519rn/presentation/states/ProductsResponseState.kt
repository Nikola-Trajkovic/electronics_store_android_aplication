package rs.raf.jul.nikola_trajkovic_4519rn.presentation.states

import rs.raf.jul.nikola_trajkovic_4519rn.data.models.ProductsResponse

sealed class ProductsResponseState {

    data class Success(val product: ProductsResponse): ProductsResponseState()
    data class Error(val message: String): ProductsResponseState()

}