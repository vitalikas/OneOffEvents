package lt.vitalijus.oneoffevents.profile.di

import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffect
import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffectDelegate
import lt.vitalijus.oneoffevents.core.presentation.util.Reducer
import lt.vitalijus.oneoffevents.profile.data.UserRepositoryImpl
import lt.vitalijus.oneoffevents.profile.domain.UserRepository
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileEffect
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileReducer
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileScreenPartialState
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileScreenState
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val userProfileModule = module {
    single(named("userProfileReducer")) {
        UserProfileReducer as Reducer<UserProfileScreenState, UserProfileScreenPartialState>
    }

    single<AutoConsumableEffect<UserProfileEffect>> {
        AutoConsumableEffectDelegate<UserProfileEffect>()
    }

    singleOf(::UserRepositoryImpl) bind UserRepository::class

    viewModel {
        UserProfileViewModel(
            userProfileReducer = get(named("userProfileReducer")),
            userProfileEffectDelegate = get(),
            userRepository = get(),
        )
    }
}
