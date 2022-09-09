package rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.CategoryState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductDetailState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductsResponseState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductsState

interface ProductsContract {

    interface ViewModel {

        val productsState: LiveData<ProductsState>
        val productsDetailState: LiveData<ProductDetailState>
        val productsResponseState: LiveData<ProductsResponseState>
        val categoryState: LiveData<CategoryState>

        fun findProduct(id: Long)

        fun getAll()
        fun filterSearch(search: String)
        fun filterCategory(category: String)
        fun getCategories()

    }

}