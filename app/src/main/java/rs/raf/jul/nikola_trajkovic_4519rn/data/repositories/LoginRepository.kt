package rs.raf.jul.nikola_trajkovic_4519rn.data.repositories


import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Login
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody


interface LoginRepository {

    fun login(body: LoginBody): Observable<Login>

}