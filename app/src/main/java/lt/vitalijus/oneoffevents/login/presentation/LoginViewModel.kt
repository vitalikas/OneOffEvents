package lt.vitalijus.oneoffevents.login.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.vitalijus.oneoffevents.core.domain.onError
import lt.vitalijus.oneoffevents.core.domain.onSuccess
import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffectDelegate
import lt.vitalijus.oneoffevents.core.presentation.util.MVIViewModel
import lt.vitalijus.oneoffevents.login.domain.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository,
    loginEffectDelegate: AutoConsumableEffectDelegate<LoginEffect>,
) : MVIViewModel<LoginUiState, LoginEvent, LoginEffect>(
    effectDelegate = loginEffectDelegate
) {
    override val initialState: LoginUiState
        get() = LoginUiState()

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginButtonClicked -> loginButtonClicked()
        }
    }

    private fun loginButtonClicked() {
        viewModelScope.launch {
            updateUiState {
                it.copy(isLoading = true)
            }

            authRepository.login()
                .onError { loginError ->
                    effectDelegate.postEffect(
                        newEffect = LoginEffect.ShowError(
                            message = loginError.message
                        )
                    )
                }
                .onSuccess {
                    effectDelegate.postEffect(
                        newEffect = LoginEffect.NavigateToScreen
                    )
                }

            updateUiState {
                it.copy(isLoading = false)
            }
        }
    }
}
