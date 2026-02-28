package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.PrizeRuleDto
import com.lkby.tracker.domain.model.PrizeRule

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