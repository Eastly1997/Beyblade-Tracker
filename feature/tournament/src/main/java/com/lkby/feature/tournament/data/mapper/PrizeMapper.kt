package com.lkby.feature.tournament.data.mapper

import com.lkby.feature.tournament.data.remote.tournament.model.PrizeDto
import com.lkby.feature.tournament.domain.model.Placement
import com.lkby.feature.tournament.domain.model.Prize

internal fun Prize.toDto() =
    PrizeDto(
        placement = placement.name,
        name = name
    )

internal fun PrizeDto.toDomain() =
    Prize(
        placement = safeEnumValueOf(placement, Placement.OTHER),
        name = name
    )