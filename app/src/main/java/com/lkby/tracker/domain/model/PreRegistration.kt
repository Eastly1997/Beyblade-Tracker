package com.lkby.tracker.domain.model

data class PreRegistration(
    val enabled: Boolean,
    val feeOverride: Double?,
    val slotLimit: Int?,
    val benefits: List<PreRegBenefit>,
    val startTime: Long,
    val endTime: Long
)

data class PreRegBenefit(
    val type: PreRegBenefitType,
    val description: String
)

enum class PreRegBenefitType {
    DISCOUNT,
    RAFFLE_ENTRY,
    GUARANTEED_SLOT,
    FREE_ITEM,
    OTHER
}