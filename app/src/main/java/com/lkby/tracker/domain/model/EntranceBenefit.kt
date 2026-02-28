package com.lkby.tracker.domain.model

data class EntranceBenefit(
    val type: EntranceBenefitType,
    val name: String,
    val value: Double?
)

enum class EntranceBenefitType {
    FOOD_VOUCHER,
    FREE_ITEM,
    FREE_DRINK,
    GIFT_CERTIFICATE,
    OTHER
}