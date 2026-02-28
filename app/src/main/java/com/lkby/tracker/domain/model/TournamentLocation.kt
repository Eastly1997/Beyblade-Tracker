package com.lkby.tracker.domain.model

data class TournamentLocation(
    val venueName: String,
    val addressLine: String,
    val city: String,
    val province: String,
    val country: String = "Philippines",
    val longitude: Double,
    val latitude: Double
)