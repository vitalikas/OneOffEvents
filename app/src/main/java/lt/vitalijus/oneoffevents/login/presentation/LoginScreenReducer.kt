package lt.vitalijus.oneoffevents.login.presentation

import lt.vitalijus.oneoffevents.core.presentation.util.Reducer

object LoginScreenReducer : Reducer<LoginScreenState, LoginScreenPartialState> {

    override fun reduce(
        state: LoginScreenState,
        partialState: LoginScreenPartialState
    ): LoginScreenState {
        return when (partialState) {
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
}
