package com.lkby.tracker.presentation.splash

sealed interface SplashEffect {
    data object NavigateToAuth: SplashEffect
    data class NavigateToHome(val userId: String): SplashEffect
}