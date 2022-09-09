package rs.raf.jul.nikola_trajkovic_4519rn.presentation.states

import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Login

sealed class LoginState {
    data class Success(val login: Login): LoginState()
    data class Error(val message: String): LoginState()
}