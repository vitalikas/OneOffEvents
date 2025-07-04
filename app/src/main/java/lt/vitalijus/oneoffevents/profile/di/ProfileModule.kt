package lt.vitalijus.oneoffevents.profile.di

import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffectDelegate
import lt.vitalijus.oneoffevents.profile.data.UserRepositoryImpl
import lt.vitalijus.oneoffevents.profile.domain.UserRepository
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileEffect
import lt.vitalijus.oneoffevents.profile.presentation.user_profile.UserProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModule = module {
    singleOf(::UserRepositoryImpl) bind UserRepository::class

    viewModel {
        UserProfileViewModel(
            userProfileEffectDelegate = AutoConsumableEffectDelegate<UserProfileEffect>(),
            userRepository = get(),
        )
    }
}
