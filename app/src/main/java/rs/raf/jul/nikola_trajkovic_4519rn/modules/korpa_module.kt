package rs.raf.jul.nikola_trajkovic_4519rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.local.StoreDataBase
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.KorpaRepository
import rs.raf.jul.nikola_trajkovic_4519rn.data.repositories.KorpaRepositoryImpl
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.KorpaViewModel

val korpaModule= module {

    viewModel { KorpaViewModel(korpaRepository = get()) }

    single<KorpaRepository> { KorpaRepositoryImpl(localDataSource = get())}

    single { get<StoreDataBase>().getKorpaDao() }

}