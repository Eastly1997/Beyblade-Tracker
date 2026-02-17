package com.lkby.tracker.data.remote.tournament

import com.lkby.tracker.data.remote.tournament.model.TournamentDto

internal interface TournamentDataSource {

    suspend fun createTournamentWithUniqueId(
        tournament: TournamentDto
    ): Boolean
}