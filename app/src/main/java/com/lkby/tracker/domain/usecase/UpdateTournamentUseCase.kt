package com.lkby.tracker.domain.usecase

import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.repository.TournamentRepository
import com.lkby.tracker.domain.usecase.model.UpdateTournamentParams

class UpdateTournamentUseCase(
    private val repository: TournamentRepository
) {
    suspend operator fun invoke(params: UpdateTournamentParams): Result<Tournament> {
        validate(params)

        val existingResult = repository.getTournamentById(params.tournamentId)

        val existing = existingResult.getOrElse {
            return Result.failure(it)
        }

        val updatedTournament = existing.copy(
            name = params.name,
            description = params.description,
            location = params.location,
            entranceFee = params.entranceFee,
            entranceBenefits = params.entranceBenefits,
            preRegistration = params.preRegistration,
            prizePool = params.prizePool,
            maxParticipants = params.maxParticipants,
            startTime = params.startTime,
            updatedAt = System.currentTimeMillis()
        )

        val saveResult = repository.updateTournament(updatedTournament)

        return saveResult.map {
            updatedTournament
        }
    }

    private fun validate(params: UpdateTournamentParams) {
        require(params.name.isNotBlank()) {
            "Tournament name cannot be empty"
        }

        require(params.maxParticipants >= 2) {
            "Minimum participants must be at least 2"
        }

        require(params.startTime > System.currentTimeMillis()) {
            "Start time must be in the future"
        }

        require(params.entranceFee.amount >= 0) {
            "Entrance fee cannot be negative"
        }
    }
}