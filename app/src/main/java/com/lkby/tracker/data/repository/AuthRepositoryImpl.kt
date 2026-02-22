package com.lkby.tracker.data.repository

import com.google.firebase.auth.FirebaseUser
import com.lkby.tracker.data.mapper.toDomain
import com.lkby.tracker.data.mapper.toUserDto
import com.lkby.tracker.data.remote.auth.AuthDataSource
import com.lkby.tracker.data.remote.user.UserDataSource
import com.lkby.tracker.domain.model.AuthResult
import com.lkby.tracker.domain.model.User
import com.lkby.tracker.domain.repository.AuthRepository

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