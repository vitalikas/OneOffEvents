package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import lt.vitalijus.oneoffevents.profile.domain.User

data class UserProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null
)
