package trade.dolcecosmetics.app.di

import trade.dolcecosmetics.app.data.repository.CartRepository
import trade.dolcecosmetics.app.data.repository.LCDSMOnboardingRepo
import trade.dolcecosmetics.app.data.repository.OrderRepository
import trade.dolcecosmetics.app.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        LCDSMOnboardingRepo(
            lcdsmOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}