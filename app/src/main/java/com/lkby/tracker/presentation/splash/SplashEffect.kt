package com.lkby.tracker.presentation.splash

sealed class SplashEffect {
    object NavigateToAuth: SplashEffect()
    object NavigateToHome: SplashEffect()
}