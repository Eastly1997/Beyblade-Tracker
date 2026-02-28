package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TournamentLocationDto(
    @SerialName("venue_name")
    val venueName: String = "",
    @SerialName("address_line")
    val addressLine: String = "",
    @SerialName("city")
    val city: String = "",
    @SerialName("province")
    val province: String = "",
    @SerialName("latitude")
    val latitude: Double = 0.0,
    @SerialName("longitude")
    val longitude: Double = 0.0
)