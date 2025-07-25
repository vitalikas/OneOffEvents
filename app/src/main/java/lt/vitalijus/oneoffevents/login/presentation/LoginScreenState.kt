package lt.vitalijus.oneoffevents.login.presentation

import lt.vitalijus.oneoffevents.core.presentation.util.UiPartialState

data class LoginScreenState(
    val isLoading: Boolean = false,
    val data: List<String> = emptyList(),
    val error: String? = null
)

sealed class LoginScreenPartialState : UiPartialState {

    object Loading : LoginScreenPartialState()
    data class Success(val data: List<String>) : LoginScreenPartialState()
    data class Error(val message: String) : LoginScreenPartialState()
}
