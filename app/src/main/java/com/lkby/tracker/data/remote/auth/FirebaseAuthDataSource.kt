package com.lkby.tracker.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.lkby.tracker.data.remote.auth.model.FirebaseAuthResult
import kotlinx.coroutines.tasks.await

internal class FirebaseAuthDataSource(
    private val auth: FirebaseAuth
): AuthDataSource {
    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override suspend fun signInWithGoogle(idToken: String): FirebaseAuthResult {

        val credential = GoogleAuthProvider.getCredential(idToken, null)

        val result = auth
            .signInWithCredential(credential)
            .await()

        val user = result.user ?: throw Exception("User is null")

        return FirebaseAuthResult(
            uid = user.uid,
            isNewUser = result.additionalUserInfo?.isNewUser == true,
            displayName = user.displayName,
            email = user.email,
            photoUrl = user.photoUrl?.toString()
        )
    }

    override fun logout() {
        auth.signOut()
    }
}