package lt.vitalijus.oneoffevents.profile.domain

import lt.vitalijus.oneoffevents.core.domain.DomainError

sealed interface UserProfileError : DomainError {

    data class DeleteProfileError(val message: String) : UserProfileError
}
