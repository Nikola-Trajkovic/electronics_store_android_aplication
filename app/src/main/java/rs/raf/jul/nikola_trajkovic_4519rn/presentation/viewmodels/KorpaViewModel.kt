package rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.KorpaRepository
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.KorpaContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.KorpaState
import timber.log.Timber

class KorpaViewModel(
    private val korpaRepository: KorpaRepository
): ViewModel(), KorpaContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val korpaState: MutableLiveData<KorpaState> = MutableLiveData()



    override fun getKorpa() {
        val subscription = korpaRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    korpaState.value = KorpaState.Success(it)
                },
                {
                    korpaState.value = KorpaState.Error("Error happened while getting")
                }
            )
        subscriptions.add(subscription)
    }


    override fun insert(title: String, description: String, price: Int) {

        val subscription = korpaRepository
            .insert(title,description,price)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    korpaState.value = KorpaState.Added
                },
                {
                    korpaState.value = KorpaState.Error("Error happened while adding")
                }
            )
        subscriptions.add(subscription)

    }

    override fun delete(id: Long) {

        val subscription = korpaRepository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    korpaState.value = KorpaState.Delete
                },
                {
                    korpaState.value = KorpaState.Error("Error happened while deleting")
                }
            )
        subscriptions.add(subscription)

    }

    override fun update(title: String, description: String, price: Int) {
        val subscription = korpaRepository
            .update(title,description,price)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    korpaState.value = KorpaState.Update

                },
                {
                    korpaState.value = KorpaState.Error("Error happened while finding")
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteAll() {
        val subscription = korpaRepository
            .deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    korpaState.value = KorpaState.Delete

                },
                {
                    korpaState.value = KorpaState.Error("Error happened while finding")
                }
            )
        subscriptions.add(subscription)
    }


}