package com.lkby.tracker.data.mapper

import com.lkby.tracker.data.remote.tournament.model.TournamentLocationDto
import com.lkby.tracker.domain.model.TournamentLocation

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