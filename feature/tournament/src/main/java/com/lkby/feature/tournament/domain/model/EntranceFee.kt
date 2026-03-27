package com.lkby.feature.tournament.domain.model

data class EntranceFee(
    val amount: Double = 0.0,
    val currency: String = "PHP",
    val paymentMethods: List<PaymentMethod> = listOf(PaymentMethod.OTHER),
    val notes: String? = null
)

enum class PaymentMethod {
    CASH,
    G_CASH,
    MAYA,
    BANK_TRANSFER,
    OTHER
}