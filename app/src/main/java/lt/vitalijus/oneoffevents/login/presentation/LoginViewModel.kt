package lt.vitalijus.oneoffevents.login.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lt.vitalijus.oneoffevents.core.domain.onError
import lt.vitalijus.oneoffevents.core.domain.onSuccess
import lt.vitalijus.oneoffevents.core.presentation.util.AutoConsumableEffectDelegate
import lt.vitalijus.oneoffevents.core.presentation.util.MVIViewModel
import lt.vitalijus.oneoffevents.core.presentation.util.Reducer
import lt.vitalijus.oneoffevents.login.domain.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository,
    loginEffectDelegate: AutoConsumableEffectDelegate<LoginEffect>,
) : MVIViewModel<LoginScreenState, LoginScreenPartialState, LoginIntent, LoginEffect>(
    effectDelegate = loginEffectDelegate
) {
    override val reducer = Reducer<LoginScreenState, LoginScreenPartialState> { state, partialState ->
        return@Reducer when (partialState) {
            LoginScreenPartialState.Loading -> state.copy(
                isLoading = true,
                error = null
            )

            is LoginScreenPartialState.Error -> state.copy(
                isLoading = false,
                error = partialState.message
            )

            is LoginScreenPartialState.Success -> state.copy(
                isLoading = false,
                error = null
            )
        }
    }

    override val initialState: LoginScreenState
        get() = LoginScreenState()

    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginButtonClicked -> loginButtonClicked()
        }
    }

    private fun loginButtonClicked() {
        viewModelScope.launch {
            updateState(LoginScreenPartialState.Loading)

            authRepository.login()
                .onError { loginError ->
                    updateState(LoginScreenPartialState.Error(loginError.message))
                    effectDelegate.postEffect(
                        newEffect = LoginEffect.ShowError(
                            message = loginError.message
                        )
                    )
                }
                .onSuccess {
                    updateState(LoginScreenPartialState.Success(listOf("some data")))
                    effectDelegate.postEffect(
                        newEffect = LoginEffect.NavigateToScreen
                    )
                }
        }
    }
}
