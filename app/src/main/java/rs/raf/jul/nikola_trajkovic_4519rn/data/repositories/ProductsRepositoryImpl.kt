package rs.raf.jul.nikola_trajkovic_4519rn.data.repositories

import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote.ProductsService
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Categories
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.ProductsResponse

class ProductsRepositoryImpl(
    private val remoteProducts : ProductsService
) : ProductsRepository {

    override fun getAll(): Observable<List<Products>> {
        return remoteProducts.getAll().map {
            it.products
        }
    }

    override fun findProduct(id: Long): Observable<ProductsResponse> {
        return remoteProducts.findProduct(id)
    }

    override fun filterSearch(search: String): Observable<List<Products>> {
        return remoteProducts.filterSearch(search).map{
            it.products
        }

    }

    override fun filterCategory(category: String): Observable<List<Products>> {
        return remoteProducts.filterCategory(category).map {
            it.products
        }
    }

    override fun getCategories(): Observable<Categories> {
        return remoteProducts.getCategories()
    }

}