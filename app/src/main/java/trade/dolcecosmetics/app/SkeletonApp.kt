package trade.dolcecosmetics.app

import android.app.Application
import trade.dolcecosmetics.app.di.dataModule
import trade.dolcecosmetics.app.di.dispatcherModule
import trade.dolcecosmetics.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class LCDSMApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@LCDSMApp)
            modules(appModules)
        }
    }
}