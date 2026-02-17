package com.lkby.tracker.domain.usecase.model

data class CreateTournamentParams(
    val name: String,
    val createdBy: String,
    val maxParticipants: Int
)