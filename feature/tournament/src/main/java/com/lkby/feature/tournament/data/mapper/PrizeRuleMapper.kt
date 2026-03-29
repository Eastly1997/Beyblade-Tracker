package com.lkby.feature.tournament.data.mapper

import com.lkby.feature.tournament.data.remote.tournament.model.PrizeRuleDto
import com.lkby.feature.tournament.domain.model.PrizeRule

internal fun PrizeRule.toDto() =
    PrizeRuleDto(
        minPlayers = minPlayers,
        maxPlayers = maxPlayers,
        prizes = prizes.map { it.toDto() }
    )

internal fun PrizeRuleDto.toDomain() =
    PrizeRule(
        minPlayers = minPlayers,
        maxPlayers = maxPlayers,
        prizes = prizes.map { it.toDomain() }
    )