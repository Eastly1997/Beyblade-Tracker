package com.lkby.tracker.data.remote.tournament

import com.lkby.tracker.data.remote.tournament.model.TournamentDto

internal interface TournamentDataSource {

    suspend fun createTournament(
        tournament: TournamentDto
    ): Boolean

    suspend fun updateTournament(
        tournament: TournamentDto
    ): Boolean

    suspend fun getTournamentById(
        id: String
    ): TournamentDto?

}