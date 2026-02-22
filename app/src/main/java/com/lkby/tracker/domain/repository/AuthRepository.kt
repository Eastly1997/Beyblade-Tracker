package com.lkby.tracker.domain.repository

import com.lkby.tracker.domain.model.AuthResult

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<AuthResult>
}
