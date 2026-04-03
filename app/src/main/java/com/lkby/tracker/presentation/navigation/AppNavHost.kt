package com.lkby.tracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lkby.common.navigation.Route
import com.lkby.feature.auth.authScreen
import com.lkby.feature.tournament.createTournamentScreen
import com.lkby.tracker.presentation.home.HomeScreen
import com.lkby.tracker.presentation.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Splash,
        modifier = modifier
    ) {
        composable<Route.Splash> { SplashScreen(navController) }
        composable<Route.Home> { HomeScreen() }

        authScreen(navController)
        createTournamentScreen(navController)
    }
}