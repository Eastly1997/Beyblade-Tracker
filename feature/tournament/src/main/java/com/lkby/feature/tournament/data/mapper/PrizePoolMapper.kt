package com.lkby.feature.tournament.data.mapper

import com.lkby.feature.tournament.data.remote.tournament.model.PrizePoolDto
import com.lkby.feature.tournament.domain.model.PrizePool
import com.lkby.feature.tournament.domain.model.PrizeType

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