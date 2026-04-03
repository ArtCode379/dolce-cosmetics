package trade.dolcecosmetics.app.di

import trade.dolcecosmetics.app.ui.viewmodel.AppViewModel
import trade.dolcecosmetics.app.ui.viewmodel.CartViewModel
import trade.dolcecosmetics.app.ui.viewmodel.CheckoutViewModel
import trade.dolcecosmetics.app.ui.viewmodel.LCDSMOnboardingVM
import trade.dolcecosmetics.app.ui.viewmodel.OrderViewModel
import trade.dolcecosmetics.app.ui.viewmodel.ProductDetailsViewModel
import trade.dolcecosmetics.app.ui.viewmodel.ProductViewModel
import trade.dolcecosmetics.app.ui.viewmodel.LCDSMSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        LCDSMSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        LCDSMOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}