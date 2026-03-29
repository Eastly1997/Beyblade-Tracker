package com.lkby.feature.tournament

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lkby.common.navigation.Route
import com.lkby.feature.tournament.presentation.create.CreateTournamentScreen

fun NavGraphBuilder.createTournamentScreen(navController: NavController) {
    composable(
        route = Route.CreateTournament.value,
        arguments = listOf(
            navArgument("userId") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getString("userId") ?: ""
        CreateTournamentScreen(
            navController = navController,
            userId = userId
        )
    }
}