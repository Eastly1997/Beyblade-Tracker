package com.lkby.tracker.data.remote.auth.model

internal data class FirebaseAuthResult(
    val uid: String,
    val isNewUser: Boolean,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?
)