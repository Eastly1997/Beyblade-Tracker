package com.lkby.feature.tournament.data.remote.tournament

import com.lkby.feature.tournament.data.remote.tournament.model.TournamentDto

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