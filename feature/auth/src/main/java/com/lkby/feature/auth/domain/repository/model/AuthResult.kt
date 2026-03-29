package com.lkby.feature.auth.domain.repository.model

data class AuthResult(
    val isNewUser: Boolean,
    val uid: String
)