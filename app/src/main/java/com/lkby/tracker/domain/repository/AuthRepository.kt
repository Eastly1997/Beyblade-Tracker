package com.lkby.tracker.domain.repository

import com.lkby.tracker.domain.model.AuthResult
import com.lkby.tracker.domain.model.User

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<AuthResult>
    fun getCurrentUser(): User?
    fun logout()
}
