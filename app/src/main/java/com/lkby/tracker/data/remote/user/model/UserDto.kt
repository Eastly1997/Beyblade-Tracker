package com.lkby.tracker.data.remote.user.model

import com.google.firebase.firestore.FieldValue

internal data class UserDto(
    val uid: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val createdAt: Any = FieldValue.serverTimestamp()
)