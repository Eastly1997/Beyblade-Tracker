package com.lkby.feature.tournament.domain.model

data class TournamentLocation(
    val venueName: String = "",
    val addressLine: String = "",
    val city: String = "",
    val province: String = "",
    val country: String = "Philippines",
    val longitude: Double = 0.0,
    val latitude: Double = 0.0
)

fun TournamentLocation.empty(): TournamentLocation {
    return TournamentLocation(

    )
}