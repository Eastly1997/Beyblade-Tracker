package com.lkby.tracker.domain.repository

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<String>
}
