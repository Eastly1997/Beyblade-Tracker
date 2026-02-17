package com.lkby.tracker.data.repository

import com.lkby.tracker.data.mapper.toDto
import com.lkby.tracker.data.remote.tournament.TournamentDataSource
import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.repository.TournamentRepository
import com.lkby.tracker.domain.usecase.model.CreateTournamentParams
import com.lkby.tracker.domain.util.IdGenerator

internal class TournamentRepositoryImpl(
    private val dataSource: TournamentDataSource
): TournamentRepository {

    companion object {
        private const val MAX_RETRY = 5
    }

    override suspend fun createTournament(params: CreateTournamentParams): Result<Tournament> {
        repeat(MAX_RETRY) {
            val newId = IdGenerator.generateTournamentId()

            val tournament = Tournament(
                id = newId,
                name = params.name,
                createdBy = params.createdBy,
                maxParticipants = params.maxParticipants,
                createdAt = System.currentTimeMillis()
            )

            val success = dataSource.createTournamentWithUniqueId(tournament.toDto())

            if(success)
                return Result.success(tournament)
        }

        return Result.failure(
            Exception("Unable to generate unique tournament ID")
        )
    }
}