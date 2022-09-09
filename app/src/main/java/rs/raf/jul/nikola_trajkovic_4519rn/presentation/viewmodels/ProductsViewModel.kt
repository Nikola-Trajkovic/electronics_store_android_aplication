package rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.ProductsRepository
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.ProductsContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel(), ProductsContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val productsState: MutableLiveData<ProductsState> = MutableLiveData()
    override val productsDetailState: MutableLiveData<ProductDetailState> = MutableLiveData()
    override val productsResponseState: MutableLiveData<ProductsResponseState> = MutableLiveData()
    override val categoryState: MutableLiveData<CategoryState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                productsRepository
                    .filterSearch(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    productsState.value = ProductsState.Success(it)
                },
                {
                    productsState.value = ProductsState.Error("Error happened while fetching data")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun findProduct(id: Long) {
        val subscription = productsRepository
            .findProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    productsDetailState.value = ProductDetailState.Success(it.images)
                    productsResponseState.value = ProductsResponseState.Success(it)
                },
                {
                    Timber.e(it)
                    productsDetailState.value = ProductDetailState.Error("Greska pri uzimanju podataka")
                    productsResponseState.value = ProductsResponseState.Error("Greska pri uzimanju podataka")
                }
            )
        subscriptions.add(subscription)
    }


    override fun getAll() {
        val subscription = productsRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    productsState.value = ProductsState.Success(it)

                },
                {
                    Timber.e(it)
                    productsState.value = ProductsState.Error("Greska pri uzimanju podataka")
                }
            )
        subscriptions.add(subscription)
    }

    override fun filterSearch(search: String) {
        publishSubject.onNext(search)
    }

    override fun filterCategory(category: String) {
        val subscription = productsRepository
            .filterCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    productsState.value = ProductsState.Success(it)

                },
                {
                    Timber.e(it)
                    productsState.value = ProductsState.Error("Greska pri uzimanju podataka")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getCategories() {
        val subscription = productsRepository
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    categoryState.value = CategoryState.Success(it)

                },
                {
                    Timber.e(it)
                    categoryState.value = CategoryState.Error("Greska pri uzimanju podataka")
                }
            )
        subscriptions.add(subscription)
    }

}