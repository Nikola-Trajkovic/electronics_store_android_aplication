package rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.LoginState

interface LoginContract {

    interface ViewModel {

        val loginState: LiveData<LoginState>

        fun login(body: LoginBody)

    }

}