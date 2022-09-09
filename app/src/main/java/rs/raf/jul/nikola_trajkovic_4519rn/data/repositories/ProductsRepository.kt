package rs.raf.jul.nikola_trajkovic_4519rn.data.repositories

import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Categories
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.ProductsResponse

interface ProductsRepository {

    fun getAll() : Observable<List<Products>>
    fun findProduct(id: Long): Observable<ProductsResponse>
    fun filterSearch(search: String) : Observable<List<Products>>
    fun filterCategory(category: String) : Observable<List<Products>>
    fun getCategories(): Observable<Categories>

}