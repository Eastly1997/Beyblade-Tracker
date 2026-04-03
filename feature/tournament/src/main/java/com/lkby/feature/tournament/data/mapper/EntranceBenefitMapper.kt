package com.lkby.feature.tournament.data.mapper

import com.lkby.common.extensions.safeEnumValueOf
import com.lkby.feature.tournament.data.remote.tournament.model.EntranceBenefitDto
import com.lkby.feature.tournament.domain.model.EntranceBenefit
import com.lkby.feature.tournament.domain.model.EntranceBenefitType


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