package rs.raf.jul.nikola_trajkovic_4519rn.presentation.states

import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Categories

sealed class CategoryState {

    data class Success(val kategorije: Categories): CategoryState()
    data class Error(val message: String): CategoryState()

}