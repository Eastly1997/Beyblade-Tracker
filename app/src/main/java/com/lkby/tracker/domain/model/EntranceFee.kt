package com.lkby.tracker.domain.model

data class EntranceFee(
    val amount: Double,
    val currency: String = "PHP",
    val paymentMethods: List<PaymentMethod>,
    val notes: String? = null
)

enum class PaymentMethod {
    CASH,
    G_CASH,
    MAYA,
    BANK_TRANSFER,
    OTHER
}