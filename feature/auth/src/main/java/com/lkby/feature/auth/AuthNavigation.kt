package com.lkby.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lkby.common.navigation.Route
import com.lkby.feature.auth.presentation.auth.AuthScreen

fun NavGraphBuilder.authScreen(navController: NavController) {
    composable<Route.Auth> {
        AuthScreen(navController = navController)
    }
}