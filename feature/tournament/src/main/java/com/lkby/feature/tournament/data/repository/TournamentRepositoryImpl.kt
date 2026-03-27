package com.lkby.feature.tournament.data.repository

import com.lkby.feature.tournament.data.mapper.toDomain
import com.lkby.feature.tournament.data.mapper.toDto
import com.lkby.feature.tournament.data.remote.tournament.TournamentDataSource
import com.lkby.feature.tournament.domain.model.Tournament
import com.lkby.feature.tournament.domain.repository.TournamentRepository

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