package rs.raf.jul.nikola_trajkovic_4519rn.presentation.states

import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products

sealed class ProductsState {

    object Loading: ProductsState()
    object DataFetched: ProductsState()
    data class Success(val products: List<Products>): ProductsState()
    data class Error(val message: String): ProductsState()

}