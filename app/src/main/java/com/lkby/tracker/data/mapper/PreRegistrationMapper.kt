package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.PreRegistrationDto
import com.lkby.tracker.domain.model.PreRegistration

internal fun PreRegistrationDto.toDomain(): PreRegistration {
    return PreRegistration(
        enabled = enabled,
        feeOverride = feeOverride,
        slotLimit = slotLimit,
        benefits = benefits.map { it.toDomain() },
        startTime = startTime,
        endTime = endTime
    )
}

internal fun PreRegistration.toDto(): PreRegistrationDto {
    return PreRegistrationDto(
        enabled = enabled,
        feeOverride = feeOverride,
        slotLimit = slotLimit,
        benefits = benefits.map { it.toDto() },
        startTime = startTime,
        endTime = endTime
    )
}