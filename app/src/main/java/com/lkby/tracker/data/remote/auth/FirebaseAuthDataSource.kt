package com.lkby.tracker.data.remote.auth

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthDataSource(
    private val firebaseAuth: FirebaseAuth
): AuthDataSource {
    override fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }
}