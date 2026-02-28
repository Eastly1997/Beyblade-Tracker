package com.lkby.tracker.domain.usecase

import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.model.TournamentStatus
import com.lkby.tracker.domain.repository.TournamentRepository
import com.lkby.tracker.domain.usecase.model.CreateTournamentParams
import com.lkby.tracker.domain.util.IdGenerator

class CreateTournamentUseCase(
    private val repository: TournamentRepository
) {
    companion object {
        private const val MAX_RETRY = 5
    }

    suspend operator fun invoke(params: CreateTournamentParams): Result<Tournament> {
        validate(params)

        repeat(MAX_RETRY) {
            val id = IdGenerator.generateTournamentId()
            val now = System.currentTimeMillis()
            val tournament = Tournament(
                id = id,
                name = params.name,
                description = params.description,
                organizerId = params.organizerId,
                type = params.type,
                format = params.format,
                location = params.location,
                entranceFee = params.entranceFee,
                entranceBenefits = params.entranceBenefit,
                preRegistration = params.preRegistration,
                prizePool = params.prizePool,
                maxParticipants = params.maxParticipants,
                status = TournamentStatus.DRAFT,
                startTime = params.startTime,
                createdAt = now,
                updatedAt = now
            )

            val result = repository.createTournament(tournament)

            if(result.isSuccess)
                return result.map { tournament }
        }

        return Result.failure(
            Exception("Unable to generate unique tournament ID")
        )
    }

    private fun validate(params: CreateTournamentParams) {

        require(params.name.isNotBlank()) {
            "Tournament name cannot be empty"
        }

        require(params.maxParticipants >= 2) {
            "Tournament must allow at least 2 participants"
        }

        require(params.startTime > System.currentTimeMillis()) {
            "Start time must be in the future"
        }

        require(params.entranceFee.amount >= 0) {
            "Entrance fee cannot be negative"
        }
    }
}