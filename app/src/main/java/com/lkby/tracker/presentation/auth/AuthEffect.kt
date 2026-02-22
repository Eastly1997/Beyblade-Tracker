package com.lkby.tracker.presentation.auth

sealed class AuthEffect {
    data object LaunchGoogleSignIn: AuthEffect()
    data object NavigateToHome: AuthEffect()
    data class ShowError(val message: String): AuthEffect()
}