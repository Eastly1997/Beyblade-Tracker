package com.lkby.feature.tournament.presentation.create

sealed class CreateTournamentNavigation {
    data class Success(val tournamentId: String) : CreateTournamentNavigation()
    object Exit : CreateTournamentNavigation()
}