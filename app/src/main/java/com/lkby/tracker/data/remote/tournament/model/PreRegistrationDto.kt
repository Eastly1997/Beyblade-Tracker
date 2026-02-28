package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PreRegistrationDto(
    @SerialName("enabled")
    val enabled: Boolean = false,
    @SerialName("fee_override")
    val feeOverride: Double? = null,
    @SerialName("slot_limit")
    val slotLimit: Int? = null,
    @SerialName("benefits")
    val benefits: List<PreRegBenefitDto> = emptyList(),
    @SerialName("start_time")
    val startTime: Long = 0,
    @SerialName("end_time")
    val endTime: Long = 0
)