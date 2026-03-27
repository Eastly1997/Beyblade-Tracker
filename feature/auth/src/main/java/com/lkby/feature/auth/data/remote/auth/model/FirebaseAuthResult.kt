package com.lkby.feature.auth.data.remote.auth.model

internal data class FirebaseAuthResult(
    val uid: String,
    val isNewUser: Boolean,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?
)