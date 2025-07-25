package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.vitalijus.oneoffevents.core.domain.onError
import lt.vitalijus.oneoffevents.core.domain.onSuccess
import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffect
import lt.vitalijus.oneoffevents.core.presentation.util.MVIViewModel
import lt.vitalijus.oneoffevents.core.presentation.util.Reducer
import lt.vitalijus.oneoffevents.profile.domain.UserRepository

class UserProfileViewModel(
    private val userRepository: UserRepository,
    userProfileEffectDelegate: AutoConsumableEffect<UserProfileEffect>
) : MVIViewModel<UserProfileScreenState, UserProfileScreenPartialState, UserProfileEvent, UserProfileEffect>(
    effectDelegate = userProfileEffectDelegate
) {

    override val reducer = Reducer<UserProfileScreenState, UserProfileScreenPartialState> { state, partial ->
        return@Reducer when (partial) {
            UserProfileScreenPartialState.Loading -> state.copy(
                isLoading = true,
                error = null
            )

            is UserProfileScreenPartialState.Error -> state.copy(
                isLoading = false,
                error = partial.message
            )

            is UserProfileScreenPartialState.Success -> state.copy(
                isLoading = false,
                error = null
            )
        }
    }

    override val initialState: UserProfileScreenState
        get() = UserProfileScreenState()

    override fun handleIntent(intent: UserProfileEvent) {
        when (intent) {
            UserProfileEvent.DeleteAccountClicked -> deleteAccount()
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch {
            updateState(UserProfileScreenPartialState.Loading)

            userRepository.deleteUserProfile()
                .onError { error ->
                    updateState(UserProfileScreenPartialState.Error(error.message))
                    effectDelegate.postEffect(
                        newEffect = UserProfileEffect.ShowError(message = error.message)
                    )
                }
                .onSuccess {
                    updateState(UserProfileScreenPartialState.Success(listOf("some data")))
                    effectDelegate.postEffect(
                        newEffect = UserProfileEffect.NavigateToScreen
                    )
                }
        }
    }
}
