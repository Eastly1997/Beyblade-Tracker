package com.lkby.tracker.presentation.auth

sealed interface AuthIntent {
    object SignInWithGoogleClicked: AuthIntent
    data class GoogleTokenReceived(val idToken: String): AuthIntent
}