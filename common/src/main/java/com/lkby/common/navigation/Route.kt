package com.lkby.common.navigation

sealed class Route(val value: String) {
    object Splash: Route("splash")
    object Auth: Route("auth")
    object Home: Route("home")
    object CreateTournament: Route("tournament_create/{userId}") {
        fun createRoute(userId: String) = "tournament_create/$userId"
    }
}