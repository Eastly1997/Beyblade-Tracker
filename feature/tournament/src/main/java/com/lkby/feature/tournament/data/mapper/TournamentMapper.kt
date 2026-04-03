package com.lkby.feature.tournament.data.mapper

import com.lkby.common.extensions.safeEnumValueOf
import com.lkby.feature.tournament.data.remote.tournament.model.TournamentDto
import com.lkby.feature.tournament.domain.model.Tournament
import com.lkby.feature.tournament.domain.model.TournamentFormat
import com.lkby.feature.tournament.domain.model.TournamentStatus
import com.lkby.feature.tournament.domain.model.TournamentType


internal fun Tournament.toDto(): TournamentDto {
    return TournamentDto(
        id = id,
        name = name,
        description = description,
        organizerId = organizerId,
        type = type.name,
        format = format.name,
        location = location.toDto(),
        entranceFee = entranceFee.toDto(),
        entranceBenefits = entranceBenefits.map { it.toDto() },
        preRegistration = preRegistration?.toDto(),
        prizePool = prizePool?.toDto() ?: com.lkby.feature.tournament.data.remote.tournament.model.PrizePoolDto(),
        maxParticipants = maxParticipants,
        status = status.name,
        startTime = startTime ?: 0L,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

internal fun TournamentDto.toDomain(): Tournament {
    return Tournament(
        id = id,
        name = name,
        description = description,
        organizerId = organizerId,
        type = safeEnumValueOf(type, TournamentType.UNOFFICIAL),
        format = safeEnumValueOf(format, TournamentFormat.SINGLE_ELIMINATION),
        location = location.toDomain(),
        entranceFee = entranceFee.toDomain(),
        entranceBenefits = entranceBenefits.map { it.toDomain() },
        preRegistration = preRegistration?.toDomain(),
        prizePool = prizePool.toDomain(),
        maxParticipants = maxParticipants,
        status = safeEnumValueOf(status, TournamentStatus.DRAFT),
        startTime = if (startTime == 0L) null else startTime,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}