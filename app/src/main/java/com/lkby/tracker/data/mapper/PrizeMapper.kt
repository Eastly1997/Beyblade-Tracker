package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.PrizeDto
import com.lkby.tracker.domain.model.Placement
import com.lkby.tracker.domain.model.Prize

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