package com.lkby.tracker.data.repository

import com.lkby.tracker.data.mapper.toDomain
import com.lkby.tracker.data.mapper.toDto
import com.lkby.tracker.data.remote.tournament.TournamentDataSource
import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.repository.TournamentRepository

internal class TournamentRepositoryImpl(
    private val dataSource: TournamentDataSource
): TournamentRepository {

    override suspend fun createTournament(tournament: Tournament): Result<Unit> =
        runCatching {
            dataSource.createTournament(tournament.toDto())
        }

    override suspend fun updateTournament(tournament: Tournament): Result<Unit> =
        runCatching {
            dataSource.updateTournament(tournament.toDto())
        }


    override suspend fun getTournamentById(tournamentId: String): Result<Tournament> =
        runCatching {
            dataSource
                .getTournamentById(tournamentId)
                ?.toDomain()
                ?: throw Exception("Tournament not found")
        }
}