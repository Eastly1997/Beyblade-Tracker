package com.lkby.tracker.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource(
    private val auth: FirebaseAuth
): AuthDataSource {
    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun signInWithGoogle(idToken: String): String {

        val credential = GoogleAuthProvider.getCredential(idToken, null)

        val result = auth
            .signInWithCredential(credential)
            .await()

        return result.user?.uid ?: throw Exception("Authentication failed")

    }
}