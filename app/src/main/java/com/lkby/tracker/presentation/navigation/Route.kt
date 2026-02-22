package com.lkby.tracker.presentation.navigation

sealed class Route(val value: String) {
    object Splash: Route("splash")
    object Auth: Route("auth")
    object Home: Route("home")
}