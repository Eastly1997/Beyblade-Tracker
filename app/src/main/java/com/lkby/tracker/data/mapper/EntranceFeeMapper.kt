package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.EntranceFeeDto
import com.lkby.tracker.domain.model.EntranceFee
import com.lkby.tracker.domain.model.PaymentMethod

internal fun EntranceFee.toDto() =
    EntranceFeeDto(
        amount = amount,
        currency = currency,
        paymentMethods = paymentMethods.map { it.name },
        notes = notes
    )

internal fun EntranceFeeDto.toDomain() =
    EntranceFee(
        amount = amount,
        currency = currency,
        paymentMethods = paymentMethods.map {
            safeEnumValueOf(it, PaymentMethod.OTHER)
        },
        notes = notes
    )