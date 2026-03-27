package com.lkby.feature.auth.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.lkby.feature.auth.data.remote.auth.model.FirebaseAuthResult

internal interface AuthDataSource {
    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithGoogle(idToken: String): FirebaseAuthResult
    fun logout()
}