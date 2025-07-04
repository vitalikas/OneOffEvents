package lt.vitalijus.oneoffevents.login.di

import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffectDelegate
import lt.vitalijus.oneoffevents.login.data.AuthRepositoryImpl
import lt.vitalijus.oneoffevents.login.domain.AuthRepository
import lt.vitalijus.oneoffevents.login.presentation.LoginEffect
import lt.vitalijus.oneoffevents.login.presentation.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    viewModel {
        LoginViewModel(
            authRepository = get(),
            loginEffectDelegate = AutoConsumableEffectDelegate<LoginEffect>()
        )
    }
}
