package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.TournamentDto
import com.lkby.tracker.domain.model.Tournament

internal fun Tournament.toDto(): TournamentDto {
    return TournamentDto(
        id = id,
        name = name,
        createdBy = createdBy,
        maxParticipants = maxParticipants,
        createdAt = createdAt
    )
}

internal fun TournamentDto.toDomain(): Tournament {
    return Tournament(
        id = id,
        name = name,
        createdBy = createdBy,
        maxParticipants = maxParticipants,
        createdAt = createdAt
    )
}