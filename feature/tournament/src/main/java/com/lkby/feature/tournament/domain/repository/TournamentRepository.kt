package com.lkby.feature.tournament.domain.repository

import com.lkby.feature.tournament.domain.model.Tournament

interface TournamentRepository {
    suspend fun createTournament(
        tournament: Tournament
    ): Result<Unit>

    suspend fun updateTournament(
        tournament: Tournament
    ): Result<Unit>

    suspend fun getTournamentById(
        tournamentId: String
    ): Result<Tournament>
}