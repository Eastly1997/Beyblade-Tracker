package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.PreRegBenefitDto
import com.lkby.tracker.domain.model.PreRegBenefit
import com.lkby.tracker.domain.model.PreRegBenefitType

internal fun PreRegBenefit.toDto(): PreRegBenefitDto {
    return PreRegBenefitDto(
        type = type.name,
        description = description
    )
}

internal fun PreRegBenefitDto.toDomain(): PreRegBenefit {
    return PreRegBenefit(
        type = safeEnumValueOf(type, PreRegBenefitType.OTHER),
        description = description
    )
}