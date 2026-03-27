package com.lkby.feature.tournament.data.mapper

import com.lkby.feature.tournament.data.remote.tournament.model.TournamentDto
import com.lkby.feature.tournament.domain.model.Tournament
import com.lkby.feature.tournament.domain.model.TournamentFormat
import com.lkby.feature.tournament.domain.model.TournamentStatus
import com.lkby.feature.tournament.domain.model.TournamentType


internal fun Tournament.toDto(): TournamentDto {
    return TournamentDto(
        id = id,
        name = name,
        organizerId = organizerId,
        maxParticipants = maxParticipants,
        createdAt = createdAt
    )
}

internal fun TournamentDto.toDomain(): Tournament {
    return Tournament(
        id = id,
        name = name,
        description = description,
        organizerId = organizerId,
        type = TournamentType.valueOf(type),
        format = TournamentFormat.valueOf(format),
        location = location.toDomain(),
        entranceFee = entranceFee.toDomain(),
        entranceBenefits = entranceBenefits.map { it.toDomain() },
        preRegistration = preRegistration?.toDomain(),
        prizePool = prizePool.toDomain(),
        maxParticipants = maxParticipants,
        status = safeEnumValueOf(status, TournamentStatus.ARCHIVED),
        startTime = startTime,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}