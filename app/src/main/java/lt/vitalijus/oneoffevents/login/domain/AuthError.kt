package lt.vitalijus.oneoffevents.login.domain

import lt.vitalijus.oneoffevents.core.domain.DomainError

sealed interface AuthError : DomainError {

    data class LoginError(val message: String) : AuthError
}
