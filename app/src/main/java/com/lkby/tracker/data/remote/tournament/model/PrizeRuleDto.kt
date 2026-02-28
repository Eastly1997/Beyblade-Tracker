package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PrizeRuleDto(

    @SerialName("min_players")
    val minPlayers: Int = 0,
    @SerialName("max_players")
    val maxPlayers: Int? = null,
    @SerialName("prizes")
    val prizes: List<PrizeDto> = emptyList()
)