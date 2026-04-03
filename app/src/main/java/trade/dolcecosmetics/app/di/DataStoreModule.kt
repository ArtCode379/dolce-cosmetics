package trade.dolcecosmetics.app.di

import trade.dolcecosmetics.app.data.datastore.LCDSMOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { LCDSMOnboardingPrefs(androidContext()) }
}