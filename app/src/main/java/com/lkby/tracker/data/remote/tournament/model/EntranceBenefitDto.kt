package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EntranceBenefitDto(
    @SerialName("type")
    val type: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("value")
    val value: Double? = null
)