package lt.vitalijus.oneoffevents.profile.presentation.user_profile

sealed interface UserProfileEvent {

    data object DeleteAccountClicked : UserProfileEvent
}
