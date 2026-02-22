package com.lkby.tracker.domain.model

import com.google.firebase.firestore.FieldValue

data class User(
    val uid: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val createdAt: Long? = null
)