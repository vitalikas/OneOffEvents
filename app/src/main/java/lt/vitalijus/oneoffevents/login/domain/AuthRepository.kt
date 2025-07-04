package lt.vitalijus.oneoffevents.login.domain

import lt.vitalijus.oneoffevents.core.domain.Result

interface AuthRepository {

    suspend fun login(): Result<Unit, AuthError.LoginError>
}
