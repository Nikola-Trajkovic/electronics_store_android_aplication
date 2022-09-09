package rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Categories
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.ObjectResponse
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.ProductsResponse


interface ProductsService {

    @GET("products")
    fun getAll(): Observable<ObjectResponse>

    @GET("products/{id}")
    fun findProduct(@Path("id") id: Long): Observable<ProductsResponse>

    @GET("products/search")
    fun filterSearch(@Query("q") search: String): Observable<ObjectResponse>

    @GET("products/category/{category}")
    fun filterCategory(@Path("category") category: String): Observable<ObjectResponse>

    @GET("products/categories")
    fun getCategories(): Observable<Categories>

}