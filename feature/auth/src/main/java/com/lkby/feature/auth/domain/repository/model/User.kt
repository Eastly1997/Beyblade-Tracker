package com.lkby.feature.auth.domain.repository.model

data class User(
    val uid: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val createdAt: Long? = null
)