package lt.vitalijus.oneoffevents.profile.presentation.user_profile

import lt.vitalijus.oneoffevents.core.presentation.util.Reducer

object UserProfileReducer : Reducer<UserProfileScreenState, UserProfileScreenPartialState> {

    override fun reduce(
        state: UserProfileScreenState,
        partialState: UserProfileScreenPartialState
    ): UserProfileScreenState {
        return when (partialState) {
            UserProfileScreenPartialState.Loading -> state.copy(
                isLoading = true,
                error = null
            )

            is UserProfileScreenPartialState.Error -> state.copy(
                isLoading = false,
                error = partialState.message
            )

            is UserProfileScreenPartialState.Success -> state.copy(
                isLoading = false,
                error = null
            )
        }
    }
}
