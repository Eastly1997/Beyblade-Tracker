package com.lkby.tracker.presentation.auth

sealed interface AuthIntent {
    data object SignInWithGoogleClicked: AuthIntent
    data class GoogleTokenReceived(val idToken: String): AuthIntent
}