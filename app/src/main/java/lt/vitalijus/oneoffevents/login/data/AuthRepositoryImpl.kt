package lt.vitalijus.oneoffevents.login.data

import kotlinx.coroutines.delay
import lt.vitalijus.oneoffevents.core.domain.Result
import lt.vitalijus.oneoffevents.login.domain.AuthError
import lt.vitalijus.oneoffevents.login.domain.AuthRepository
import java.util.Random

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(): Result<Unit, AuthError.LoginError> {
        return try {
            // Simulate deletion
            delay(1000)
            val randomBoolean = Random().nextBoolean()
            if (randomBoolean) {
                Result.Success(Unit)
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            Result.Error(
                error = AuthError.LoginError(
                    message = "Something went wrong..."
                )
            )
        }
    }
}
