package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.EntranceBenefitDto
import com.lkby.tracker.domain.model.EntranceBenefit
import com.lkby.tracker.domain.model.EntranceBenefitType

internal fun EntranceBenefit.toDto() =
    EntranceBenefitDto(
        type = type.name,
        name = name,
        value = value
    )

internal fun EntranceBenefitDto.toDomain() =
    EntranceBenefit(
        type = safeEnumValueOf(type, EntranceBenefitType.OTHER),
        name = name,
        value = value
    )