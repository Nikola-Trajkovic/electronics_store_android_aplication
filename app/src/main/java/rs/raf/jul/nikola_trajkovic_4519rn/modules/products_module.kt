package rs.raf.jul.nikola_trajkovic_4519rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote.ProductsService
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.ProductsRepository
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.ProductsRepositoryImpl
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.ProductsViewModel

val productsModule = module {

    viewModel { ProductsViewModel(productsRepository = get()) }

    single<ProductsRepository> { ProductsRepositoryImpl(remoteProducts = get()) }

    single<ProductsService> { create(retrofit = get()) }
}