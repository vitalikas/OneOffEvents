package lt.vitalijus.oneoffevents.profile.data

import kotlinx.coroutines.delay
import lt.vitalijus.oneoffevents.core.domain.Result
import lt.vitalijus.oneoffevents.profile.domain.UserProfileError
import lt.vitalijus.oneoffevents.profile.domain.UserRepository
import java.util.Random

class UserRepositoryImpl : UserRepository {

    override suspend fun deleteUserProfile(): Result<Unit, UserProfileError.DeleteProfileError> {
        return try {
            // Simulate deletion and result
            delay(1000)
            val randomBoolean = Random().nextBoolean()
            if (randomBoolean) {
                Result.Success(Unit)
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            Result.Error(
                error = UserProfileError.DeleteProfileError(
                    message = "Something went wrong..."
                )
            )
        }
    }
}
