package com.lkby.tracker.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.lkby.tracker.data.remote.auth.model.FirebaseAuthResult
import com.lkby.tracker.domain.model.User

internal interface AuthDataSource {
    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithGoogle(idToken: String): FirebaseAuthResult
    fun logout()
}