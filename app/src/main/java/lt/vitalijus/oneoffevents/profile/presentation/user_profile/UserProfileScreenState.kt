package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import lt.vitalijus.oneoffevents.core.presentation.util.UiPartialState
import lt.vitalijus.oneoffevents.profile.domain.User

data class UserProfileScreenState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

sealed class UserProfileScreenPartialState : UiPartialState {

    object Loading : UserProfileScreenPartialState()
    data class Success(val data: List<String>) : UserProfileScreenPartialState()
    data class Error(val message: String) : UserProfileScreenPartialState()
}
