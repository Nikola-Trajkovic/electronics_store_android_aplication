package rs.raf.jul.nikola_trajkovic_4519rn.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.jul.nikola_trajkovic_4519rn.modules.coreModule
import rs.raf.jul.nikola_trajkovic_4519rn.modules.korpaModule
import rs.raf.jul.nikola_trajkovic_4519rn.modules.loginModule
import rs.raf.jul.nikola_trajkovic_4519rn.modules.productsModule
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            loginModule,
            productsModule,
            korpaModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@App)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }
}