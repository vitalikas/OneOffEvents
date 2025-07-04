package lt.vitalijus.oneoffevents.login.presentation

interface LoginEffect {

    data object NavigateToScreen : LoginEffect
    data class ShowError(val message: String?) : LoginEffect
}
