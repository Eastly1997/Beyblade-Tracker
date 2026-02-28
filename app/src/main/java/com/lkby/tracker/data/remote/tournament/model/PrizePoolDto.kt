package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PrizePoolDto(
    @SerialName("type")
    val type: String = "",
    @SerialName("rules")
    val rules: List<PrizeRuleDto> = emptyList()
)