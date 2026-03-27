package com.lkby.feature.tournament.domain.usecase

import com.lkby.common.util.IdProvider
import com.lkby.feature.tournament.domain.model.EntranceFee
import com.lkby.feature.tournament.domain.model.Tournament
import com.lkby.feature.tournament.domain.model.TournamentLocation
import com.lkby.feature.tournament.domain.model.TournamentStatus
import com.lkby.feature.tournament.domain.model.TournamentType
import com.lkby.feature.tournament.domain.repository.TournamentRepository
import com.lkby.feature.tournament.domain.usecase.model.CreateTournamentParams

class CreateTournamentUseCase(
    private val repository: TournamentRepository,
    private val idProvider: IdProvider
) {
    companion object {
        private const val MAX_RETRY = 5
        private const val ID_LENGTH = 6
    }

    suspend operator fun invoke(params: CreateTournamentParams): Result<Tournament> {
        validate(params)

        repeat(MAX_RETRY) {
            val id = idProvider.generateId(ID_LENGTH)
            val now = System.currentTimeMillis()
            val tournament = Tournament(
                id = id,
                name = params.name,
                description = "",
                organizerId = params.organizerId,
                type = TournamentType.UNOFFICIAL,
                format = params.format,
                location = TournamentLocation(),
                entranceFee = EntranceFee(),
                entranceBenefits = emptyList(),
                preRegistration = null,
                prizePool = null,
                maxParticipants = 999,
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
        require(params.name.isNotBlank())
    }
}