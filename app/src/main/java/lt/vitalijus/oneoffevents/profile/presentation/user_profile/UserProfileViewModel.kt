package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.vitalijus.oneoffevents.core.domain.onError
import lt.vitalijus.oneoffevents.core.domain.onSuccess
import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffect
import lt.vitalijus.oneoffevents.core.presentation.util.MVIViewModel
import lt.vitalijus.oneoffevents.core.presentation.util.Reducer
import lt.vitalijus.oneoffevents.profile.domain.UserRepository

class UserProfileViewModel(
    userProfileReducer: Reducer<UserProfileScreenState, UserProfileScreenPartialState>,
    private val userRepository: UserRepository,
    userProfileEffectDelegate: AutoConsumableEffect<UserProfileEffect>
) : MVIViewModel<UserProfileScreenState, UserProfileScreenPartialState, UserProfileEvent, UserProfileEffect>(
    effectDelegate = userProfileEffectDelegate
) {

    init {
        Log.d("reducer", "Reducer instance injected: ${userProfileReducer::class.java.name} (hash: ${userProfileReducer.hashCode()})")
        Log.d("reducer", "LoginViewModel instance created: ${this.hashCode()}")
    }

    override val reducer = userProfileReducer

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
