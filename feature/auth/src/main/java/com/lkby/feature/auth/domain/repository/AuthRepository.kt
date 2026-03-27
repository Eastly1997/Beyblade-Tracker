package com.lkby.feature.auth.domain.repository

import com.lkby.feature.auth.domain.repository.model.AuthResult
import com.lkby.feature.auth.domain.repository.model.User


interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<AuthResult>
    fun getCurrentUser(): User?
    fun logout()
}
