package com.lkby.feature.tournament.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PrizeDto(
    @SerialName("placement")
    val placement: String = "",
    @SerialName("name")
    val name: String = ""
)