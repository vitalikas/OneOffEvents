package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.vitalijus.oneoffevents.core.domain.onError
import lt.vitalijus.oneoffevents.core.domain.onSuccess
import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffect
import lt.vitalijus.oneoffevents.core.presentation.util.MVIViewModel
import lt.vitalijus.oneoffevents.profile.domain.UserRepository

class UserProfileViewModel(
    private val userRepository: UserRepository,
    userProfileEffectDelegate: AutoConsumableEffect<UserProfileEffect>
) : MVIViewModel<UserProfileUiState, UserProfileEvent, UserProfileEffect>(
    effectDelegate = userProfileEffectDelegate
) {

    override val initialState: UserProfileUiState
        get() = UserProfileUiState()

    override fun handleEvent(event: UserProfileEvent) {
        when (event) {
            UserProfileEvent.DeleteAccountClicked -> deleteAccount()
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch {
            updateUiState {
                it.copy(isLoading = true)
            }

            userRepository.deleteUserProfile()
                .onError { error ->
                    effectDelegate.postEffect(
                        newEffect = UserProfileEffect.ShowError(message = error.message)
                    )
                }
                .onSuccess {
                    effectDelegate.postEffect(
                        newEffect = UserProfileEffect.NavigateToScreen
                    )
                }

            updateUiState {
                it.copy(isLoading = false)
            }
        }
    }
}
