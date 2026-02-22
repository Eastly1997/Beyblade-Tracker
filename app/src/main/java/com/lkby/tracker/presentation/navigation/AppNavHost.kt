package com.lkby.tracker.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lkby.tracker.presentation.auth.AuthScreen
import com.lkby.tracker.presentation.home.HomeScreen
import com.lkby.tracker.presentation.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Splash.value,
        modifier = modifier
    ) {

        composable(Route.Splash.value) {
            SplashScreen(navController)
        }

        composable(Route.Auth.value) {
            AuthScreen(navController)
        }

        composable(Route.Home.value) {
            HomeScreen()
        }

    }

}