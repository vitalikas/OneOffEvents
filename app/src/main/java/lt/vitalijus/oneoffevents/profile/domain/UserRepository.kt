package lt.vitalijus.oneoffevents.profile.domain

import lt.vitalijus.oneoffevents.core.domain.Result

interface UserRepository {

    suspend fun deleteUserProfile(): Result<Unit, UserProfileError.DeleteProfileError>
}
