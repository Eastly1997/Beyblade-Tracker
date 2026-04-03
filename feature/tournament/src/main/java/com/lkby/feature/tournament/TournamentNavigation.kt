package com.lkby.feature.tournament

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lkby.common.navigation.Route
import com.lkby.feature.tournament.presentation.create.CreateTournamentScreen

fun NavGraphBuilder.createTournamentScreen(navController: NavController) {
    composable<Route.CreateTournament> { backStackEntry ->
        val route: Route.CreateTournament = backStackEntry.toRoute()
        CreateTournamentScreen(
            navController = navController,
            userId = route.userId
        )
    }
}