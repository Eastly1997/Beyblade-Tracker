package com.lkby.tracker.domain.usecase

import com.lkby.tracker.domain.model.Tournament
import com.lkby.tracker.domain.repository.TournamentRepository
import com.lkby.tracker.domain.usecase.model.CreateTournamentParams

class CreateTournamentUseCase(
    private val repository: TournamentRepository
) {

    suspend operator fun invoke(params: CreateTournamentParams): Result<Tournament> {
        validate(params)
        return repository.createTournament(params)
    }

    private fun validate(params: CreateTournamentParams) {

    }
}