package rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels

import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.LoginRepository
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.LoginContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.LoginState
import timber.log.Timber

class LoginViewModel(
    private val loginRepository:LoginRepository
) : ViewModel() , LoginContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val loginState: MutableLiveData<LoginState> = MutableLiveData()


    override fun login(body: LoginBody) {

        val subscription = loginRepository
            .login(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    loginState.value = LoginState.Success(it)

                },
                {
                    Timber.e(it)
                    loginState.value = LoginState.Error("Greska pri loginovanju")
                }
            )
        subscriptions.add(subscription)

    }



}