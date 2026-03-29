package com.lkby.feature.tournament.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PreRegBenefitDto(
    @SerialName("type")
    val type: String = "",
    @SerialName("description")
    val description: String = ""
)