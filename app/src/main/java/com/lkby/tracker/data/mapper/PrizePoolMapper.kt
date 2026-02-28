package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.PrizePoolDto
import com.lkby.tracker.domain.model.PrizePool
import com.lkby.tracker.domain.model.PrizeType

internal fun PrizePool.toDto() =
    PrizePoolDto(
        type = type.name,
        rules = rules.map { it.toDto() }
    )

internal fun PrizePoolDto.toDomain() =
    PrizePool(
        type = safeEnumValueOf(type, PrizeType.OTHER),
        rules = rules.map { it.toDomain() }
    )