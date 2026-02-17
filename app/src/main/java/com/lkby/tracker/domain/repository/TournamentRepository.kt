package com.lkby.tracker.domain.repository

import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.usecase.model.CreateTournamentParams

interface TournamentRepository {
    suspend fun createTournament(
        params: CreateTournamentParams
    ): Result<Tournament>
}