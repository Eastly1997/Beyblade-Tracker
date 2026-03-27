package com.lkby.feature.tournament.domain.usecase.model

import com.lkby.feature.tournament.domain.model.TournamentFormat

data class CreateTournamentParams(
    val name: String,
    val organizerId: String,
    val startTime: Long?,
    val format: TournamentFormat
)