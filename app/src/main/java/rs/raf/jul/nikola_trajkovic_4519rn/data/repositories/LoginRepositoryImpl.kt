package rs.raf.jul.nikola_trajkovic_4519rn.data.repositories

import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote.LoginService
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Login
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody

class LoginRepositoryImpl(
    private val remoteLogin: LoginService
): LoginRepository{

    override fun login(body: LoginBody): Observable<Login> {
        return remoteLogin.login(body).map {
            Login(
                it.id,
                it.username,
                it.email,
                it.firstName,
                it.lastName,
                it.gender,
                it.image
            )
        }
    }

}