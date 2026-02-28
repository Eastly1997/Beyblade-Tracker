package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EntranceFeeDto(
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("currency")
    val currency: String = "PHP",
    @SerialName("payment_methods")
    val paymentMethods: List<String> = emptyList(),
    @SerialName("notes")
    val notes: String? = null
)