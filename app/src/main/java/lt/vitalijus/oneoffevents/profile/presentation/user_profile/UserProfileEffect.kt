package lt.vitalijus.oneoffevents.profile.presentation.user_profile

interface UserProfileEffect {

    data class ShowError(val message: String) : UserProfileEffect
    data object NavigateToScreen : UserProfileEffect
}
