package com.lkby.feature.tournament.data.mapper

import com.lkby.common.extensions.safeEnumValueOf
import com.lkby.feature.tournament.data.remote.tournament.model.EntranceFeeDto
import com.lkby.feature.tournament.domain.model.EntranceFee
import com.lkby.feature.tournament.domain.model.PaymentMethod

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