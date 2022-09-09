package rs.raf.jul.nikola_trajkovic_4519rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote.LoginService
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.LoginRepository
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.LoginRepositoryImpl
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.LoginViewModel

val loginModule = module {

    viewModel { LoginViewModel(loginRepository = get()) }

    single<LoginRepository> { LoginRepositoryImpl(remoteLogin = get()) }

    single<LoginService> { create(retrofit = get()) }
}