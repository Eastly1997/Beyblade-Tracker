package com.lkby.tracker.domain.repository

import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.usecase.model.CreateTournamentParams

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