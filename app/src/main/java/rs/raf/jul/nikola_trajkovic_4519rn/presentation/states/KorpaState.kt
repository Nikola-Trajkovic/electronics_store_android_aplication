package rs.raf.jul.nikola_trajkovic_4519rn.presentation.states

import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa

sealed class KorpaState {

    data class Success(val korpa: List<Korpa>): KorpaState()
    data class Error(val message: String): KorpaState()
    object Delete: KorpaState()
    object Added: KorpaState()
    object Update: KorpaState()


}