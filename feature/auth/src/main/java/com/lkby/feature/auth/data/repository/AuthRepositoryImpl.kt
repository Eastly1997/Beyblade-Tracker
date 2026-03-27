package com.lkby.feature.auth.data.repository


import com.lkby.feature.auth.data.mapper.toDomain
import com.lkby.feature.auth.data.mapper.toUserDto
import com.lkby.feature.auth.data.remote.auth.AuthDataSource
import com.lkby.feature.auth.data.remote.user.UserDataSource
import com.lkby.feature.auth.domain.repository.AuthRepository
import com.lkby.feature.auth.domain.repository.model.AuthResult
import com.lkby.feature.auth.domain.repository.model.User

internal class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val userDataSource: UserDataSource
): AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<AuthResult> {
        return try {
           val result = authDataSource.signInWithGoogle(idToken)

            if(result.isNewUser) {
                val userDto = result.toUserDto()
                userDataSource.createUser(userDto)
            }

            Result.success(
                AuthResult(
                    uid = result.uid,
                    isNewUser = result.isNewUser
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): User? {
        return authDataSource.getCurrentUser()?.toDomain()
    }

    override fun logout() {
        authDataSource.logout()
    }

}