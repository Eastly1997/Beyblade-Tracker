package com.lkby.tracker.domain.model

data class AuthResult(
    val isNewUser: Boolean,
    val uid: String
)