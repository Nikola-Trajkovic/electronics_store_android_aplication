package rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginResponse

interface LoginService {

    @POST("auth/login")
    fun login(@Body data: LoginBody): Observable<LoginResponse>

}