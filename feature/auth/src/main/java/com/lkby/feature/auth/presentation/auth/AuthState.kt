package com.lkby.feature.auth.presentation.auth

data class AuthState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val error: String? = null
)