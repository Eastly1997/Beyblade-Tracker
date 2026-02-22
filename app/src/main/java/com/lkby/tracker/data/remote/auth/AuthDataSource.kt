package com.lkby.tracker.data.remote.auth

import com.lkby.tracker.data.remote.auth.model.FirebaseAuthResult
import com.lkby.tracker.data.remote.user.model.UserDto

internal interface AuthDataSource {
    fun getCurrentUserId(): String?

    suspend fun signInWithGoogle(idToken: String): FirebaseAuthResult

    suspend fun logout()
}