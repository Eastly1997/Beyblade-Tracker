package com.lkby.feature.tournament.data.mapper

import com.lkby.feature.tournament.data.remote.tournament.model.TournamentLocationDto
import com.lkby.feature.tournament.domain.model.TournamentLocation

internal fun TournamentLocation.toDto() =
    TournamentLocationDto(
        venueName = venueName,
        addressLine = addressLine,
        city = city,
        province = province,
        latitude = latitude,
        longitude = longitude
    )

internal fun TournamentLocationDto.toDomain() =
    TournamentLocation(
        venueName = venueName,
        addressLine = addressLine,
        city = city,
        province = province,
        latitude = latitude,
        longitude = longitude
    )