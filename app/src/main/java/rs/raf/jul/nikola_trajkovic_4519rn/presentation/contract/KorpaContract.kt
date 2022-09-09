package rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.KorpaState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.LoginState

interface KorpaContract {

    interface ViewModel {

        val korpaState: LiveData<KorpaState>

        fun getKorpa()
        fun insert(title: String, description: String, price: Int)
        fun delete(id: Long)
        fun update(title: String, description: String, price: Int)
        fun deleteAll()

    }

}