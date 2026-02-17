package com.lkby.tracker.data.remote.auth

interface AuthDataSource {
    fun getCurrentUserId(): String?

    suspend fun signInWithGoogle(idToken: String): String
}