package lt.vitalijus.oneoffevents.login.di

import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffectDelegate
import lt.vitalijus.oneoffevents.core.presentation.util.Reducer
import lt.vitalijus.oneoffevents.login.data.AuthRepositoryImpl
import lt.vitalijus.oneoffevents.login.domain.AuthRepository
import lt.vitalijus.oneoffevents.login.presentation.LoginEffect
import lt.vitalijus.oneoffevents.login.presentation.LoginScreenPartialState
import lt.vitalijus.oneoffevents.login.presentation.LoginScreenReducer
import lt.vitalijus.oneoffevents.login.presentation.LoginScreenState
import lt.vitalijus.oneoffevents.login.presentation.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {
    single(named("loginReducer")) {
        LoginScreenReducer as Reducer<LoginScreenState, LoginScreenPartialState>
    }

    single {
        AutoConsumableEffectDelegate<LoginEffect>()
    }

    viewModel {
        LoginViewModel(
            loginReducer = get(named("loginReducer")),
            loginEffectDelegate = get(),
            authRepository = get()
        )
    }

    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}
