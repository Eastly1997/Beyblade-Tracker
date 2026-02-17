package com.lkby.tracker.data.repository

import com.lkby.tracker.data.remote.auth.AuthDataSource
import com.lkby.tracker.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val dataSource: AuthDataSource
): AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<String> {
        return try {
            val uid = dataSource.signInWithGoogle(idToken)

            Result.success(uid)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}